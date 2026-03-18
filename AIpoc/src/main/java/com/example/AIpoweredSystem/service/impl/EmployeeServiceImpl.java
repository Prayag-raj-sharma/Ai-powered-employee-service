package com.example.AIpoweredSystem.service.impl;

import com.example.AIpoweredSystem.dto.EmployeeRequest;
import com.example.AIpoweredSystem.entity.Employee;
import com.example.AIpoweredSystem.exception.ResourceNotFoundException;
import com.example.AIpoweredSystem.repository.IEmployeeRepository;
import com.example.AIpoweredSystem.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository repository;
    @Override
    public Employee create(EmployeeRequest request) {
        Employee employee = Employee.builder()
                            .name(request.getName())
                            .department(request.getDepartment())
                            .salary(request.getSalary())
                            .build();
        return repository.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Employee Not Found"));
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }
}
