package com.vijendra.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vijendra.dto.CreateEmployeeRequest;
import com.vijendra.dto.EmployeeResponse;
import com.vijendra.dto.UpdateEmployeeNameRequest;
import com.vijendra.model.Employee;
import com.vijendra.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable int id) {
        return toEmployee(this.employeeService.getEmployee(id));
    }

    @GetMapping()
    public List<EmployeeResponse> getEmployees() {
        List<Employee> employees = this.employeeService.getAllEmployees();
        return employees.stream().map(employee -> toEmployee(employee)).toList();
    }

    @PostMapping()
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        Employee employee = this.employeeService.create(this.getEmployeeFromRequest(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toEmployee(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedEmployee(@PathVariable("id") int employeeId) {
        this.employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatEmployee(@Valid @RequestBody UpdateEmployeeNameRequest employee, @PathVariable int id) {
        this.employeeService.updateEmployeeName(id, employee.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private Employee getEmployeeFromRequest(CreateEmployeeRequest request) {
        Employee employee = new Employee(request.getName(), request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        return employee;
    }

    private EmployeeResponse toEmployee(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getSalary());
    }
}
