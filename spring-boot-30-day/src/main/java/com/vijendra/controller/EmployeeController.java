package com.vijendra.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijendra.model.Employee;
import com.vijendra.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return this.employeeService.getEmployee(id);
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @PostMapping()
    public Employee createEmployee(@RequestBody Employee employee) {
        return this.employeeService.create(employee);
    }

    @DeleteMapping("/{id}")
    public boolean deletedEmployee(@PathVariable("id") int employeeId) {
        return this.employeeService.deleteEmployee(employeeId);
    }

    @PatchMapping("/{id}")
    public boolean updatEmployee(@RequestBody Employee employee, @PathVariable int id) {
        return this.employeeService.updateEmployeeName(id, employee.getName());
    }
}
