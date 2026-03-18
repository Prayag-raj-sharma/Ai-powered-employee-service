package com.example.AIpoweredSystem.repository;

import com.example.AIpoweredSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
