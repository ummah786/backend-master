package com.hesabbook.controller;

import com.hesabbook.dto.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        return message;
    }

    @MessageMapping("/signal")
    @SendTo("/topic/signal")
    public String handleSignal(Object signal) {
        return signal.toString();
    }


}