package com.vijendra.dao.spring_jdbc;

import com.vijendra.dto.SpringJdbcData;
import com.vijendra.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;

    public EmployeeDAO(JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionManager = transactionManager;
    }

    public List<Employee> getAll() {
        String query = "SELECT * FROM employees";

        return jdbcTemplate.query(query, getRowMapper());
    }

    public Employee create(Employee employee) {
        String query = """
                INSERT INTO employees(name, email, salary, department)
                VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            query,
                            new String[]{"id"}
                    );

                    ps.setString(1, employee.getName());
                    ps.setString(2, employee.getEmail());
                    ps.setBigDecimal(3, employee.getSalary());
                    ps.setString(4, employee.getDepartment());

                    return ps;
                },
                keyHolder
        );

        Number insertedEmployeeId = keyHolder.getKey();

        if (insertedEmployeeId == null) {
            throw new IllegalStateException("Generated employee ID was not returned");
        }

        int employeeId = insertedEmployeeId.intValue();
        return get(employeeId);
    }

    public Employee get(int id) {
        String query = "SELECT * FROM employees WHERE id = ?";

/*        if(id == 1)
            throw new RuntimeException("Throwing Exception........................");*/
        return jdbcTemplate.queryForObject(
                query,
                getRowMapper(),
                id
        );
    }

    public boolean updateByName(int id, String name) {
        String query = "UPDATE employees SET name = ? WHERE id = ?";

        return jdbcTemplate.update(query, name, id) == 1;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM employees WHERE id = ?";

        return jdbcTemplate.update(query, id) == 1;
    }

    public boolean insertAndUpdateWithTransactionManager(
            Employee employee,
            BigDecimal salary
    ) {
        TransactionDefinition transactionDefinition =
                new DefaultTransactionDefinition();

        TransactionStatus transactionStatus =
                transactionManager.getTransaction(transactionDefinition);

        try {
            String insertQuery = """
                    INSERT INTO employees(name, email, salary, department)
                    VALUES (?, ?, ?, ?)
                    """;

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(
                                insertQuery,
                                Statement.RETURN_GENERATED_KEYS
                        );

                        ps.setString(1, employee.getName());
                        ps.setString(2, employee.getEmail());
                        ps.setBigDecimal(3, BigDecimal.ZERO);
                        ps.setString(4, employee.getDepartment());

                        return ps;
                    },
                    keyHolder
            );

            Number idNumber = keyHolder.getKey();

            if (idNumber == null) {
                throw new IllegalStateException(
                        "Generated employee ID was not returned"
                );
            }

            int newEmployeeId = idNumber.intValue();

            String updateQuery = """
                    UPDATE employees
                    SET salary = ?
                    WHERE id = ?
                    """;

            jdbcTemplate.update(
                    updateQuery,
                    salary,
                    newEmployeeId
            );

            transactionManager.commit(transactionStatus);

            return true;

        } catch (RuntimeException e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }
    }

    public boolean insertAndUpdate(Employee employee, BigDecimal salary) {

        String insertQuery = """
                INSERT INTO employees(name, email, salary, department)
                VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            insertQuery,
                            Statement.RETURN_GENERATED_KEYS
                    );

                    ps.setString(1, employee.getName());
                    ps.setString(2, employee.getEmail());
                    ps.setBigDecimal(3, BigDecimal.ZERO);
                    ps.setString(4, employee.getDepartment());

                    return ps;
                },
                keyHolder
        );

        Number idNumber = keyHolder.getKey();

        if (idNumber == null) {
            throw new IllegalStateException(
                    "Generated employee ID was not returned"
            );
        }

        int newEmployeeId = idNumber.intValue();

        String updateQuery = """
                UPDATE employees
                SET salary = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                updateQuery,
                salary,
                newEmployeeId
        );

        return true;
    }

    private RowMapper<Employee> getRowMapper() {

        return (resultSet, rowNum) -> {

            Employee employee = new Employee(
                    resultSet.getString("name"),
                    resultSet.getString("email")
            );

            employee.setId(resultSet.getInt("id"));
            employee.setSalary(resultSet.getBigDecimal("salary"));
            employee.setDepartment(resultSet.getString("department"));

            return employee;
        };
    }
}
