package com.example.AIpoweredSystem.service;

import com.example.AIpoweredSystem.dto.ApiResponse;
import com.example.AIpoweredSystem.dto.LogResponse;
import com.example.AIpoweredSystem.entity.AIQueryLog;
import org.springframework.data.domain.Page;


import java.util.concurrent.CompletableFuture;

public interface IAIService {
    CompletableFuture<String> askAI(String query);

    LogResponse getLogs(int page, int size);
}
