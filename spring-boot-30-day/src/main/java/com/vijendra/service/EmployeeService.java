package com.vijendra.service;

import com.vijendra.annotation.LogExecution;
import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import com.vijendra.entity.EmployeeEntity;
import com.vijendra.model.Employee;
import com.vijendra.repository.EmployeeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final PaymentGateway paymentGateway;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeDAO employeeDAO, PaymentGateway paymentGateway,
            EmployeeRepository employeeRepository) {
        this.employeeDAO = employeeDAO;
        this.paymentGateway = paymentGateway;
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employee.getName());
        employeeEntity.setEmail(employee.getEmail());
        // employeeEntity.setDepartment(employee.getDepartment());
        employeeEntity.setSalary(employee.getSalary());

        employeeEntity = employeeRepository.save(employeeEntity);
        return toEmployee(employeeEntity);
        // return employeeDAO.create(employee);
    }

    @Transactional
    public Employee getEmployee(int employeeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        if(employeeEntity.isEmpty()) {
            throw new NoSuchElementException("Employee with id %d not found.".formatted(employeeId));
        }

        employeeEntity.get().getDepartment().getName();
        return toEmployee(employeeEntity.get());
        // return employeeDAO.get(employeeId);
    }

    public boolean deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
        return true;
        // return employeeDAO.delete(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepository
                .findAll().stream()
                .map(employeeEntity -> toEmployee(employeeEntity)).toList();
        // return employeeDAO.getAll();
    }

    public boolean updateEmployeeName(int id, String name) {
        Optional<EmployeeEntity> employeeOptional = this.employeeRepository.findById(id);
        if(employeeOptional.isEmpty()) {
            throw new NoSuchElementException("Employee with id %d not found to update.".formatted(id));
        }

        EmployeeEntity employeeEntity = employeeOptional.get();
        employeeEntity.setName(name);
        this.employeeRepository.save(employeeEntity);
        return true;
    }

    public boolean insertAndUpdateWithTransactionManager(
            Employee employee,
            BigDecimal salary) {
        return employeeDAO.insertAndUpdateWithTransactionManager(
                employee,
                salary);
    }

    public boolean insertAndUpdate(
            Employee employee,
            BigDecimal salary) {
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

        // throw new RuntimeException("BAD happened in A");
    }

    private Employee toEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee(employeeEntity.getName(), employeeEntity.getEmail());
        employee.setDepartment(employeeEntity.getDepartment().getName());
        employee.setSalary(employeeEntity.getSalary());
        employee.setId(employeeEntity.getId());
        return employee;
    }

    // @Transactional(readOnly = true)
    // public Page<Employee> getEmployees(Pageable pageable) {
    //     return this.employeeRepository.findAll(pageable).map(this::toEmployee);
    // }
    @Transactional(readOnly = true)
    public Page<Employee> getEmployees(Pageable pageable) {
        return this.employeeRepository.findAll(pageable).map(this::toEmployee);
    }
}
