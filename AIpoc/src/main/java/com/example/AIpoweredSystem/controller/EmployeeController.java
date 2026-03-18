package com.example.AIpoweredSystem.controller;

import com.example.AIpoweredSystem.dto.ApiResponse;
import com.example.AIpoweredSystem.dto.EmployeeRequest;
import com.example.AIpoweredSystem.entity.Employee;
import com.example.AIpoweredSystem.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;


    @PostMapping
    public ApiResponse<Employee> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee emp = employeeService.create(request);
        return new ApiResponse<>(true, emp);
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getEmployee(@PathVariable Long id) {
        return new ApiResponse<>(true, employeeService.getById(id));
    }

    @GetMapping
    public ApiResponse<List<Employee>> getAllEmployee() {
        return new ApiResponse<>(true, employeeService.getAll());
    }
}
