package com.vijendra.service;

import com.vijendra.repository.EmployeeRepository;
import com.vijendra.model.Employee;

import java.util.List;

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

    public boolean deleteEmployee(int employeeId) {
        return this.employeeRepository.delete(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository.getAll();
    }

    public Employee updateEmployeeName(int id, String name) {
        return this.employeeRepository.updateByName(id, name);
    }
}
