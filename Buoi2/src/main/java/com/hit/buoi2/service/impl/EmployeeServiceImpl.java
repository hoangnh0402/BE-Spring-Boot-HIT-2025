package com.hit.buoi2.service.impl;

import com.hit.buoi2.entity.Employee;
import com.hit.buoi2.repository.EmployeeRepository;
import com.hit.buoi2.service.EmployeeService;
import jakarta.persistence.Embeddable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
