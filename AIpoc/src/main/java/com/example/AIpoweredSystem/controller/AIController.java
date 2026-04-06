package com.example.AIpoweredSystem.controller;

import com.example.AIpoweredSystem.dto.ApiResponse;
import com.example.AIpoweredSystem.dto.LogResponse;
import com.example.AIpoweredSystem.entity.AIQueryLog;
import com.example.AIpoweredSystem.service.IAIService;
import com.example.AIpoweredSystem.service.ratelimiter.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {
    private final IAIService aiService;
    private final RateLimiterService rateLimiterService;

    @GetMapping("/ask")
    public CompletableFuture<ApiResponse<String>> askAI(@RequestParam String query, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        if (!rateLimiterService.isAllowed(clientIp)) {
            throw new RuntimeException("Rate limit exceeded");
        }
        return aiService.askAI(query)
                .thenApply(response->
                        new ApiResponse<>(true, response));
    }

    @GetMapping("/logs")
    public ApiResponse<LogResponse> getLogs(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size) {
        LogResponse response = aiService.getLogs(page, size);
        return new ApiResponse<>(true, response);
    }
}
