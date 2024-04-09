package com.hesabbook.controller;

import com.hesabbook.dto.ChatMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class ChatController {
    @MessageMapping("/signal")
    @SendTo("/topic/signal")
    public String handleSignal(Object signal) {
        return signal.toString();
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String sendMessage(byte[] message) {
        return new String(message);
    }
}