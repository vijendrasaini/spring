package com.vijendra;

import com.vijendra.config.AppConfig;
import com.vijendra.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);
        SpringJdbcDemo.showEmployee(employeeService.getEmployee(1));
    }
}
