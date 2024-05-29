package com.hesabbook.controller;

import java.util.HashMap;
import java.util.Map;

import com.hesabbook.dto.ChatMessage;
import com.hesabbook.dto.Message;
import com.hesabbook.dto.SignalMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @MessageMapping("/sendSignal")
    @SendToUser("/topic/receiveSignal")
    public Map<String, String>  sendSignal(Map<String, String> signalMessage) {
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
 /*   @MessageMapping("/sendSignal")
    @SendTo("/topic/receiveSignal")
    public SignalMessage sendSignald(SignalMessage message) {
        return message;
    }*/

    /*@MessageMapping("/sendSignal")
    public void sendSignal(SignalMessage signalMessage) {
        messagingTemplate.convertAndSendToUser(signalMessage.getTo(), "/user/topic/receiveSignal", signalMessage);
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage chatMessage) {
        messagingTemplate.convertAndSendToUser(chatMessage.getTo(), "/topic/receiveMessage", chatMessage);
        messagingTemplate.convertAndSend("/topic/receiveMessage", chatMessage);
        messagingTemplate.convertAndSend("/user/topic/receiveMessage", chatMessage);
    }*/
/*    @MessageMapping("/sendSignal")
    public void sendSignal(SignalMessage signalMessage) {
        messagingTemplate.convertAndSendToUser(signalMessage.getTo(), "/topic/receiveSignal", signalMessage);
    }*/
    /* @MessageMapping("/hello")
      @SendTo("/topic/greetings")
      public Message sendMessage(Message message) {
          // Send the message to the user-specific topic
          //   messagingTemplate.convertAndSendToUser(recipientUserId, "/queue/messages", message);
          return message;
      }
  */
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