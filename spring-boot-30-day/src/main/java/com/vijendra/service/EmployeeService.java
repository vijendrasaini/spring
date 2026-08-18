package com.vijendra.service;

import com.vijendra.annotation.LogExecution;
import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import com.vijendra.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final PaymentGateway paymentGateway;

    public EmployeeService(EmployeeDAO employeeDAO, PaymentGateway paymentGateway) {
        this.employeeDAO = employeeDAO;
        this.paymentGateway = paymentGateway;
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

    @Transactional
    public void testTransaction() throws Exception {
        System.out.println("Inside service");
        int employeeId1 = 1, employeeId2 = 2;
        System.out.println("Updating Employee with Id : " + employeeId1);
        employeeDAO.updateByName(1, "RAM 6");
        System.out.println("Updating Employee with Id : " + employeeId2);
        employeeDAO.updateByName(4, "Laxman 6");
        throw new Exception("Something went wrong");
    }

    @Transactional
    public void updateName() {
        System.out.println("Updating name for User 1...");
        employeeDAO.updateByName(1, "Time1 " + LocalTime.now());

        paymentGateway.pay(this.employeeDAO);

        //throw new RuntimeException("BAD happened in A");
    }
}
