package com.vijendra.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijendra.model.Employee;
import com.vijendra.service.EmployeeService;
import com.vijendra.service.JPAEmployeeService;

@RestController
@RequestMapping("/test")
public class TestController {
    private final EmployeeService employeeService;
    private final JPAEmployeeService jpaEmployeeService;
    public TestController(EmployeeService employeeService, JPAEmployeeService jpaEmployeeService) {
        this.employeeService = employeeService;
        this.jpaEmployeeService = jpaEmployeeService;
    }

    @GetMapping("/")
    public void test() {
        Employee employee = new Employee("Test", "test@test.in");
        employee.setDepartmentId(1);
        employee.setSalary(new BigDecimal("555.55"));
        // employee = this.jpaEmployeeService.persistAndFind(employee);

        // System.out.println("Here is the employee id : " + employee.getId());
    }
}
