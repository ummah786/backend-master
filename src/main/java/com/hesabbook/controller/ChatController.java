package com.hesabbook.controller;

import com.hesabbook.dto.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class ChatController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
 /*   @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message sendMessage(Message message) {

        // Send the message to the user-specific topic
        //   messagingTemplate.convertAndSendToUser(recipientUserId, "/queue/messages", message);

        return message;
    }*/

    @MessageMapping("/hello")
    public void sendMessageValue(Message message) {
        //  messagingTemplate.convertAndSendToUser(recipientUserId, "/topic/greetings", message);
        String destination = "/topic/greetings/"+message.getReceiver();
        messagingTemplate.convertAndSend(destination, message);
        messagingTemplate.convertAndSend("/topic/greetings", message);
        messagingTemplate.convertAndSend("/topic/greetings/asif", message);
    }

    @MessageMapping("/signal")
    @SendTo("/topic/signal")
    public String handleSignal(Object signal) {
        return signal.toString();
    }


}