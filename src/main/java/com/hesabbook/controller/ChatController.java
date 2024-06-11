package com.hesabbook.controller;

import java.util.HashMap;
import java.util.Map;

import com.hesabbook.dto.Message;
import com.hesabbook.dto.SignalMessage;
import com.hesabbook.dto.WebRTCMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    private final Map<String, String> userSessions = new HashMap<>();


    @MessageMapping("/register")
    public void registerUser(Map<String, String> payload) {
        userSessions.put(payload.get("name"), payload.get("id"));
    }

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/offer")
    public void handleOffer(Object message) {
        messagingTemplate.convertAndSend("/topic/webrtc-offer/" + "asif", message);
    }

    @MessageMapping("/answer")
    public void handleAnswer(Object message) {
        messagingTemplate.convertAndSend("/topic/webrtc-answer/"+ "asif", message);
    }

    @MessageMapping("/candidate")
    public void handleCandidate(Object message) {
        messagingTemplate.convertAndSend("/topic/webrtc-candidate/" + "asif", message);
    }

    @MessageMapping("/sendSignal")
    @SendToUser("/topic/receiveSignal")
    public Map<String, String> sendSignal(Map<String, String> signalMessage) {
        String to = signalMessage.get("to");
        String sessionId = userSessions.get(to);
        if (to != null) {
            String destination = "/topic/receiveSignal" + to;
            messagingTemplate.convertAndSend(destination, signalMessage);
            messagingTemplate.convertAndSendToUser(to, "/topic/receiveSignal", signalMessage);
        }
        return signalMessage;
    }

    @MessageMapping("/sendMessage")
    @SendToUser("/topic/receiveMessage")
    public Map<String, String> sendMessage(Map<String, String> chatMessage) {
        String destination = "/topic/receiveMessage/" + chatMessage.get("to");
        messagingTemplate.convertAndSend(destination, chatMessage);
        return chatMessage;


    }
    @MessageMapping("/hello")
    public void sendMessageValue(Message message) {
        //  messagingTemplate.convertAndSendToUser(recipientUserId, "/topic/greetings", message);
        String destination = "/topic/greetings/" + message.getReceiver();
        messagingTemplate.convertAndSend(destination, message);
        messagingTemplate.convertAndSend("/topic/greetings", message);
        messagingTemplate.convertAndSend("/topic/greetings/asif", message);
    }

    @MessageMapping("/signal")
    @SendTo("/topic/signal")
    public String handleSignal(Object signal) {
        return signal.toString();
    }


    @MessageMapping("/screen-share")
    @SendTo("/topic/screen-share")
    public SignalMessage handleScreenShare(SignalMessage message) {
        return message; // Forward the message to all subscribers
    }

}