package com.vijendra.day3_spring_jdbc;

import com.vijendra.model.Employee;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

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

        String salaryUpdateQuery = "update employees set salary = ? where id = ?";
        int updateCount = jdbcTemplate.update(salaryUpdateQuery, new BigDecimal("49500.88"), 12);
        if(updateCount == 1) {
            System.out.println("Salary has been updated for Employee ID : 12");
        }

        String updateQuery =
                "UPDATE employees SET name = ?, salary = ? WHERE id = ?";

        int rowsAffected = jdbcTemplate.update(
                updateQuery,
                "Bhisma 2.0",
                new BigDecimal("99999.99"),
                12
        );

        System.out.println("Rows updated : " + rowsAffected);

        String getAllQuery = "select id, name, email, salary from employees";
        RowMapper<Employee> rowMapper = (resultSet, rowNum) -> {
            Employee employee = new Employee(resultSet.getString("name"), resultSet.getString("email"));
            employee.setId(resultSet.getInt("id"));
            employee.setSalary(resultSet.getBigDecimal("salary"));
            return employee;
        };

        List<Employee> employeeList = jdbcTemplate.query(getAllQuery, rowMapper);
        for(Employee employee : employeeList) {
            System.out.println("-----------------Employee-----------------");
            System.out.println("ID : " + employee.getId());
            System.out.println("Name : " + employee.getName());
            System.out.println("Email : " + employee.getEmail());
            System.out.println("Salary : " + employee.getSalary());
        }


        String insertQuery = "insert into employees(name, email, salary, department) values( ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int insertUpdateCount = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, "Vishnu");
            ps.setString(2, "lordvishnu@gmail.com");
            ps.setBigDecimal(3, new BigDecimal("99999.11"));
            ps.setString(4, "benkuth");

            return ps;
        }, keyHolder);

        Number employeeId = keyHolder.getKey();
        System.out.println("New generated ID : " + employeeId);
    }
}
