package com.vijendra.service;

import com.vijendra.dao.raw_jdbc.EmployeeDAO;
import com.vijendra.model.Employee;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class EmployeeRawJDBCService {
    private final EmployeeDAO employeeDAO;

    public EmployeeRawJDBCService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee create(Employee employee) {
        return this.employeeDAO.create(employee);
    }

    public Employee getEmployee(int employeeId) {
        return this.employeeDAO.get(employeeId);
    }

    public boolean deleteEmployee(int employeeId) {
        return this.employeeDAO.delete(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAll();
    }

    public Employee updateEmployeeName(int id, String name) {
        return this.employeeDAO.updateByName(id, name);
    }

    public boolean insertAndUpdate(Employee employee, BigDecimal salary) throws SQLException {
        return this.employeeDAO.insertAndUpdate(employee, salary);
    }
}
