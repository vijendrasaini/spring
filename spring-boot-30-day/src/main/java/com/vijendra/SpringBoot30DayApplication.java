package com.vijendra;

import com.vijendra.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class SpringBoot30DayApplication {
    static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(SpringBoot30DayApplication.class, args);

        DataSource dataSource =
                context.getBean(DataSource.class);

        JdbcTemplate jdbcTemplate =
                context.getBean(JdbcTemplate.class);

        PlatformTransactionManager transactionManager =
                context.getBean(PlatformTransactionManager.class);

        EmployeeService employeeService = context.getBean(EmployeeService.class);

        System.out.println("JDBC TEMPLATE : " + jdbcTemplate);
        System.out.println("DATASOURCE : " + dataSource);
        System.out.println("TRANSACTION MANAGER : " + transactionManager);
        System.out.println("Employees : " + employeeService.getEmployee(1));
    }
}
