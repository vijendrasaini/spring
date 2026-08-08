package com.vijendra.service;

import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import com.vijendra.model.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public int create(Employee employee) {
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

    public boolean updateEmployeeName(int id, String name) {
        return this.employeeDAO.updateByName(id, name);
    }

    public boolean insertAndUpdateWithTransactionManager(Employee employee, BigDecimal salary) throws SQLException {
        return this.employeeDAO.insertAndUpdateWithTransactionManager(employee, salary);
    }

    @Transactional
    public boolean insertAndUpdate(Employee employee, BigDecimal salary) throws SQLException {
        return this.employeeDAO.insertAndUpdate(employee, salary);
    }
}
