package com.vijendra.dao.spring_jdbc;

import com.vijendra.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EmployeeDAO {
    private final JdbcTemplate jdbcTemplate;
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll() {
        return List.of();
    }
}
