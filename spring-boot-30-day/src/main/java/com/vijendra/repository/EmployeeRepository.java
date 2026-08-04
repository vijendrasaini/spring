package com.vijendra.repository;

import com.vijendra.model.Employee;

import java.sql.*;

public class EmployeeRepository {
    private final String dbHost;
    private final String dbUser;
    private final String dbPassword;
    private final String dbName;

    public EmployeeRepository() {
        this.dbName = "spring_boot_30_day";
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
                PreparedStatement ps = connection.prepareStatement(query);
                ) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setDouble(3, employee.getSalary());
            ps.setString(4, employee.getDepartment());

            int rowsAffected = ps.executeUpdate();

            return employee;
        } catch (SQLException e) {

            System.out.println("Error while cretaing the Employee : " + e.getMessage());
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
            ResultSet res = ps.executeQuery();

            return mapEmployee(res);
        } catch(SQLException e) {
            System.out.println("Error while getting Emoplyee. Error : " + e.getMessage());
            return null;
        }
    }

    public Employee mapEmployee(ResultSet rs) throws SQLException {
        Employee employee = null;
        if(rs.next()) {
            employee = new Employee(rs.getString("name"), rs.getString("email"));
            employee.setSalary(rs.getDouble("salary"));
            employee.setDepartment(rs.getString("department"));
            employee.setId(rs.getInt("id"));
        }

        return employee;
    }
}
