package com.example.AIpoweredSystem.repository;

import com.example.AIpoweredSystem.entity.AIQueryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAIQueryLogRepository extends JpaRepository<AIQueryLog, Long> {
}
