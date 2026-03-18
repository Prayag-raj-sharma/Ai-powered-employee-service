package com.example.AIpoweredSystem.service;

import com.example.AIpoweredSystem.dto.EmployeeRequest;
import com.example.AIpoweredSystem.entity.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee create(EmployeeRequest request);

    Employee getById(Long id);

    List<Employee> getAll();
}
