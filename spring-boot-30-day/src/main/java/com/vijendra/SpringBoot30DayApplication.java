package com.vijendra;

import com.vijendra.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBoot30DayApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBoot30DayApplication.class, args);
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        SpringJdbcDemo.showEmployee(employeeService.getEmployee(1));
    }
}
