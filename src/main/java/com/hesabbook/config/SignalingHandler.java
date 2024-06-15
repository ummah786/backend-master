package  com.hesabbook.config;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignalingHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        session.sendMessage(new TextMessage("{\"type\": \"me\", \"id\": \"" + session.getId() + "\"}"));
        System.out.println("New client connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Assuming the incoming message is in JSON format
        String payload = message.getPayload();
        Map<String, String> msg = parseMessage(payload);

        switch (msg.get("type")) {
            case "callUser":
                handleCallUser(msg, session);
                break;
            case "answerCall":
                handleAnswerCall(msg, session);
                break;
        }
    }

    private void handleCallUser(Map<String, String> msg, WebSocketSession session) throws IOException {
        String userToCall = msg.get("userToCall");
        String signalData = msg.get("signalData");
        String from = msg.get("from");
        String name = msg.get("name");

        System.out.println("callUser event: from " + from + " to " + userToCall + ", name: " + name);

        WebSocketSession userSession = sessions.get(userToCall);
        if (userSession != null && userSession.isOpen()) {
            userSession.sendMessage(new TextMessage("{\"type\": \"callUser\", \"signal\": \"" + signalData + "\", \"from\": \"" + from + "\", \"name\": \"" + name + "\"}"));
        }
    }

    private void handleAnswerCall(Map<String, String> msg, WebSocketSession session) throws IOException {
        String to = msg.get("to");
        String signal = msg.get("signal");

        System.out.println("answerCall event: from " + to);

        WebSocketSession userSession = sessions.get(to);
        if (userSession != null && userSession.isOpen()) {
            userSession.sendMessage(new TextMessage("{\"type\": \"callAccepted\", \"signal\": \"" + signal + "\"}"));
        }
    }

    private Map<String, String> parseMessage(String payload) {
        // Simple JSON parsing, in real application use a library like Jackson or Gson
        Map<String, String> msg = new HashMap<>();
        payload = payload.replace("{", "").replace("}", "").replace("\"", "");
        String[] pairs = payload.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            msg.put(keyValue[0].trim(), keyValue[1].trim());
        }
        return msg;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
        sessions.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage("{\"type\": \"callEnded\"}"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Client disconnected: " + session.getId());
    }
}
