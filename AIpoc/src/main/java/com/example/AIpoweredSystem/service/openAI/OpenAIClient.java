package com.example.AIpoweredSystem.service.openAI;

import com.example.AIpoweredSystem.config.OpenAIConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OpenAIClient {

    private final OpenAIConfig config;

    private final RestTemplate restTemplate;

    @Retry(name = "openai", fallbackMethod = "fallback")
    @CircuitBreaker(name = "openai", fallbackMethod = "fallback")
    public String call(String query) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(config.getApiKey());
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = Map.of(
                    "model", "gpt-4o-mini",
                    "message", List.of(
                            Map.of("role", "user", "content", query)
                    )
            );

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(
                            config.getUrl(),
                            request,
                            Map.class
                    );
            return extractResponse(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("OpenAI call failed:", e);
        }
    }
    private String extractResponse(Map body) {

        List choices = (List) body.get("choices");
        Map firstChoice = (Map) choices.get(0);
        Map message = (Map) firstChoice.get("message");

        return message.get("content").toString();
    }

    // Fallback method
    private String fallback(String query, Throwable ex) {

        System.out.println("Fallback triggers:" + ex.getMessage());

        return "AI service is temporarily unavailable. Please try again later...";
    }
}
