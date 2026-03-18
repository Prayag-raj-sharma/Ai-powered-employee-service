package com.example.AIpoweredSystem.service.impl;

import com.example.AIpoweredSystem.dto.LogResponse;
import com.example.AIpoweredSystem.entity.AIQueryLog;
import com.example.AIpoweredSystem.repository.IAIQueryLogRepository;
import com.example.AIpoweredSystem.service.IAIService;
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

    @Async
    @Override
    public CompletableFuture<String> askAI(String query) {
        String response = "AI response for: " + query;

        AIQueryLog log = AIQueryLog.builder()
                .query(query)
                .response(response)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(log);

        return CompletableFuture.completedFuture(response);
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
