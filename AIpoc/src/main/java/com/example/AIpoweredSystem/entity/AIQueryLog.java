package com.example.AIpoweredSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIQueryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    @Column(length = 2000)
    private String response;

    private LocalDateTime createdAt;

    private long responseTimeMs;

    private boolean cached;
}
