package com.vijendra.dao.raw_jdbc;

import com.vijendra.model.Employee;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final String dbHost;
    private final String dbUser;
    private final String dbPassword;
    private final String dbName;

    public EmployeeDAO() {
        this.dbName = "spring_boot_30_days";
        this.dbHost = "jdbc:mysql://localhost:3306/" + this.dbName;
        this.dbUser = "root";
        this.dbPassword = "root";
    }

    public Employee create(Employee employee) {
        String query = """
                    INSERT INTO employees(name, email, salary, department)
                    VALUES (?, ?, ?, ?)
                """;
        try(
                Connection connection = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setBigDecimal(3, employee.getSalary());
            ps.setInt(4, 1);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 1) {
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    employee.setId(rs.getInt(1));
                }

                return employee;
            }

            System.out.println("Employee could not be inserted into the table.");
            return null;
        } catch (SQLException e) {

            System.out.println("Error while cretaing the Employee : " + e.getMessage());
            return null;
        }
    }

    public Employee updateByName(int id, String name) {
        String query = "update employees set name = ? where id = ?";

        try(
                Connection connection = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);
                PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(2, id);
            ps.setString(1, name);

            int rowAffected = ps.executeUpdate();
            if(rowAffected == 1) {
                return get(id);
            }

            return null;
        } catch(SQLException e) {
            System.out.println("Employee with id " + id + " couldn't be updated. Error : " + e.getMessage());
            return null;
        }
    }

    public Employee get(int employeeId) {
        String query = "select * from employees where id = ?";

        try(
                Connection connection = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);
                PreparedStatement ps = connection.prepareStatement(query);
                ) {
            ps.setInt(1, employeeId);
            try(ResultSet res = ps.executeQuery()) {
                if(res.next()) {
                    return mapEmployee(res);
                }

                return null;
            }
        } catch(SQLException e) {
            System.out.println("Error while getting Emoplyee. Error : " + e.getMessage());
            return null;
        }
    }

    public List<Employee> getAll() {
        String query = "select * from employees";

        List<Employee> list = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);
                PreparedStatement ps = connection.prepareStatement(query);
                ) {

            try(ResultSet res = ps.executeQuery(query)) {
                while(res.next()) {
                    list.add(mapEmployee(res));
                }
            }

            return list;
        } catch(SQLException e) {
            System.out.println("Error while getting Emoplyee. Error : " + e.getMessage());
            return list;
        }
    }

    public boolean delete(int employeeId) {
        String query = "delete from employees where id = ?";

        try(
                Connection connection = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);
                PreparedStatement ps = connection.prepareStatement(query);
                ) {
            ps.setInt(1, employeeId);
            int affectedCount = ps.executeUpdate();
            if(affectedCount == 1) {
                return true;
            }

            System.out.println("Employee with ID : " + employeeId + " not found.");
            return false;
        } catch(SQLException e) {
            System.out.println("Error while getting Emoplyee. Error : " + e.getMessage());
            return false;
        }
    }

    private Employee mapEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee(rs.getString("name"), rs.getString("email"));

        employee.setSalary(rs.getBigDecimal("salary"));
        employee.setDepartmentId(1);
        employee.setId(rs.getInt("id"));
        return employee;
    }

    public boolean insertAndUpdate(Employee employee, BigDecimal salary) throws SQLException {
        Connection connection = DriverManager.getConnection(this.dbHost, this.dbUser, this.dbPassword);
        try {
            connection.setAutoCommit(false);
            String insertQuery = """
                    INSERT INTO employees(name, email, salary, department)
                    VALUES (?, ?, ?, ?)
                """;

            String updateQuery = """
                    update employees set salary2 = ? where id = ?
                    """;

            try(
                    PreparedStatement ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement ps2 = connection.prepareStatement(updateQuery);
                    ) {
                ps.setString(1, employee.getName());
                ps.setString(2, employee.getEmail());
                ps.setBigDecimal(3, employee.getSalary());
                ps.setInt(4, 1);

                int rowsAffected = ps.executeUpdate();
                if(rowsAffected == 1) {
                    try(
                            ResultSet rs = ps.getGeneratedKeys();
                            ) {
                        if(rs.next()) {
                            int employeeId = rs.getInt(1);
                            System.out.println("NEW Employee ID : " + employeeId);

                            ps2.setBigDecimal(1, salary);
                            ps2.setInt(2, employeeId);

                            int updateCount = ps2.executeUpdate();
                        }
                    }
                }
            }

            connection.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong. Error message : " + e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.close();
        }
    }
}
