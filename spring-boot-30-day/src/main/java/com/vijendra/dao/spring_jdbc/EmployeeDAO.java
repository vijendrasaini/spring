package com.vijendra.dao.spring_jdbc;

import com.vijendra.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class EmployeeDAO {
    private final JdbcTemplate jdbcTemplate;
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll() {
        String query = "SELECT * FROM employees";
        return this.jdbcTemplate.query(query, this.getRowMapper());
    }

    public Employee create(Employee employee) {
        String query = "insert into employees(name, email, salary, department) values( ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connectin -> {
                    PreparedStatement ps = connectin.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, employee.getName());
                    ps.setString(2, employee.getEmail());
                    ps.setBigDecimal(3, employee.getSalary());
                    ps.setString(4, employee.getDepartment());
                    return ps;
                },
                keyHolder
        );

        Number insertedEmployeeId = keyHolder.getKey();
        if(insertedEmployeeId == null) {
            throw new RuntimeException("ID can't be retrieved");
        }

        int id = (int) insertedEmployeeId;
        return get(id);
    }

    public RowMapper<Employee> getRowMapper() {

        return (resultSet, rowNum) -> {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            Employee employee = new Employee(name, email);
            employee.setSalary(resultSet.getBigDecimal("salary"));
            employee.setDepartment(resultSet.getString("department"));

            return employee;
        };
    }

    public Employee get(int id) {
        String query = "Select * from employee where id = ?";
        return jdbcTemplate.query(query, getRowMapper()).getFirst();
    }
}
