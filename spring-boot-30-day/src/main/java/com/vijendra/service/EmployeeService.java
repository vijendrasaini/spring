package com.vijendra.service;

import com.vijendra.repository.EmployeeRepository;
import com.vijendra.model.Employee;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.create(employee);
    }

    public Employee getEmployee(int employeeId) {
        return this.employeeRepository.get(employeeId);
    }
}
