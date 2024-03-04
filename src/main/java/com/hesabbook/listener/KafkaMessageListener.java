package com.hesabbook.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {
    @KafkaListener(topics = "JavaTopic")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
        // Add your message handling logic here
    }
}
