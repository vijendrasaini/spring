package com.vijendra;

import com.vijendra.service.EmployeeService;
import com.vijendra.service.PaymentGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringBoot30DayApplication {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(SpringBoot30DayApplication.class, args);
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        employeeService.updateName();
    }
}
