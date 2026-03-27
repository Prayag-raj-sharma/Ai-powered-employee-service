package com.example.AIpoweredSystem.service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "ai-topic", groupId = "ai-group")
    public void consume(String message) {
        System.out.println("Received from kafka:" + message);
    }
}
