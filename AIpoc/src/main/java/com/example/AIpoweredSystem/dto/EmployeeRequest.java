package com.example.AIpoweredSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String department;

    @Positive
    private double salary;

}