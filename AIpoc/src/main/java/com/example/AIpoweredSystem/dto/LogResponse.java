package com.example.AIpoweredSystem.dto;

import com.example.AIpoweredSystem.entity.AIQueryLog;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LogResponse {
    private List<AIQueryLog> logs;

    private long total;
}
