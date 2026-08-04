package com.vijendra.repository;

import com.vijendra.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setBigDecimal(3, employee.getSalary());
            ps.setString(4, employee.getDepartment());

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
                Statement ps = connection.createStatement();
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

    public Employee mapEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee(rs.getString("name"), rs.getString("email"));

        employee.setSalary(rs.getBigDecimal("salary"));
        employee.setDepartment(rs.getString("department"));
        employee.setId(rs.getInt("id"));
        return employee;
    }
}
