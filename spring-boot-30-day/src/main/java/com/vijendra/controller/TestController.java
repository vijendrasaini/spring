package com.vijendra.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijendra.model.Employee;
import com.vijendra.service.EmployeeService;

@RestController
public class TestController {
    private final EmployeeService employeeService;
    public TestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index() {
        return "Day 10 web layer is up";
    }
}
