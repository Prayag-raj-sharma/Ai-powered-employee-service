package com.example.AIpoweredSystem.service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "ai-topic";

    public void send(String message) {
        kafkaTemplate.send(TOPIC, message);

        System.out.println("Sent to kafka:" + message);
    }
}
