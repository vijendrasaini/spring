package com.vijendra.service;

import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import com.vijendra.model.Employee;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public int create(Employee employee) {
        return employeeDAO.create(employee);
    }

    public Employee getEmployee(int employeeId) {
        return employeeDAO.get(employeeId);
    }

    public boolean deleteEmployee(int employeeId) {
        return employeeDAO.delete(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAll();
    }

    public boolean updateEmployeeName(int id, String name) {
        return employeeDAO.updateByName(id, name);
    }

    public boolean insertAndUpdateWithTransactionManager(
            Employee employee,
            BigDecimal salary
    ) {
        return employeeDAO.insertAndUpdateWithTransactionManager(
                employee,
                salary
        );
    }

    public boolean insertAndUpdate(
            Employee employee,
            BigDecimal salary
    ) {
        return employeeDAO.insertAndUpdate(employee, salary);
    }
}
