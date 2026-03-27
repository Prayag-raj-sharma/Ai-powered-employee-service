package com.example.AIpoweredSystem.service.impl;

import com.example.AIpoweredSystem.dto.LogResponse;
import com.example.AIpoweredSystem.entity.AIQueryLog;
import com.example.AIpoweredSystem.repository.IAIQueryLogRepository;
import com.example.AIpoweredSystem.service.IAIService;
import com.example.AIpoweredSystem.service.cache.CacheService;
import com.example.AIpoweredSystem.service.kafka.KafkaProducer;
import com.example.AIpoweredSystem.service.openAI.OpenAIClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements IAIService {

    private final IAIQueryLogRepository repository;
    private final CacheService cacheService;
    private final OpenAIClient openAIClient;
    private final KafkaProducer kafkaProducer;

    @Async
    @Override
    public CompletableFuture<String> askAI(String query) {
        long start = System.currentTimeMillis();

        // Check Redis Cache
        String cachedResponse = cacheService.get(query);

        if(cachedResponse != null) {

            saveLog(query, cachedResponse, true, start);

            return CompletableFuture.completedFuture(cachedResponse);
        }

        // Call openAI
        String response = openAIClient.call(query);

        // Save to Redis cache
        cacheService.set(query, response);

        // Save to database
        saveLog(query, response, false, start);

        // Publish Kafka event
        kafkaProducer.send("User asked:" + query);

        return CompletableFuture.completedFuture(response);
    }

    private void saveLog(String query, String response, boolean cached, long start) {
        AIQueryLog log = AIQueryLog.builder()
                .query(query)
                .response(response)
                .createdAt(LocalDateTime.now())
                .cached(cached)
                .responseTimeMs(System.currentTimeMillis()- start)
                .build();

        repository.save(log);
    }

    @Override
    public LogResponse getLogs(int page, int size) {
        Page<AIQueryLog> pageResult = repository.findAll(
                PageRequest.of(
                        page,
                        size,
                        Sort.by("createdAt").descending())
        );
        return new LogResponse(
                pageResult.getContent(),
                pageResult.getTotalElements()
        );
    }
}
