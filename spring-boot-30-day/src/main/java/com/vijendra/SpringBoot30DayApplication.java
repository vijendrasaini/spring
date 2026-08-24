package com.vijendra;

import com.vijendra.model.Employee;
import com.vijendra.service.EmployeeService;
import com.vijendra.service.PaymentGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringBoot30DayApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBoot30DayApplication.class, args);
        // ApplicationContext context = new AnnotationConfigApplicationContext(SpringBoot30DayApplication.class);
        // EmployeeService employeeService = context.getBean(EmployeeService.class);

        // try {
        //     Employee employee = employeeService.getEmployee(1);
        //     System.out.println("employee name: " + employee.getName());
        // } catch (Exception e) {
        //     System.out.println("Exception caught: " + e.getMessage());
        // }
    }
}
