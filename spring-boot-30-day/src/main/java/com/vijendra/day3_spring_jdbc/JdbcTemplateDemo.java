package com.vijendra.day3_spring_jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplateDemo {
    static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/spring_boot_30_day");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setMaximumPoolSize(1);
        hikariConfig.setConnectionTimeout(2000);

        DataSource dataSource = new HikariDataSource(hikariConfig);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String query = "select count(*) from employees";
        Integer employeesCount = jdbcTemplate.queryForObject(query, Integer.class);

        System.out.println("Total Employees : " + employeesCount);
    }
}
