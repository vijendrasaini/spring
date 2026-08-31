package com.vijendra.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vijendra.entity.DepartmentEntity;
import com.vijendra.entity.EmployeeEntity;
import com.vijendra.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;

@Service
public class JPAEmployeeService {
    private final EntityManager entityManager;
    private final EmployeeRepository employeeRepository;
    public JPAEmployeeService(EntityManager em, EmployeeRepository er) {
        this.entityManager = em;
        this.employeeRepository = er;
    }

    @Transactional // must for update queries
    public int updateSalaryByDepartment(String deptName, BigDecimal salary) {
        return this.employeeRepository.updateSalaryByDepartment(deptName, salary);
    }

    @Transactional // must for update queries
    public int deleteByEmail(String email) {
        return this.employeeRepository.deleteByEmail(email);
    }

    @Transactional
    public EmployeeEntity getEmployee(int id) {
        EmployeeEntity employeeEntity = this.entityManager.find(EmployeeEntity.class, id);
        if(employeeEntity == null) {
            throw new NoSuchElementException("Employee Not found!");
        }

        return employeeEntity;
    }

    @Transactional
    public String getDepartmentByEmployeeId(int employeeId) {
        EmployeeEntity employeeEntity = this.entityManager.find(EmployeeEntity.class, employeeId);
        return employeeEntity.getDepartment().getName();
    }

    @Transactional
    public DepartmentEntity getDepartment(int departmentId) {
        DepartmentEntity departmentEntity = this.entityManager.find(DepartmentEntity.class, departmentId);
        if(departmentEntity == null) {
            throw new NoSuchElementException("Department Not found!");
        }

        return departmentEntity;
    }

    // @Transactional
    // public Employee persistAndFind(Employee employee) {
    //     EmployeeEntity employeeEntity = new EmployeeEntity();

    //     employeeEntity.setName(employee.getName());
    //     employeeEntity.setEmail(employee.getEmail());
    //     employeeEntity.setDepartment(employee.getDepartment());
    //     employeeEntity.setSalary(employee.getSalary());

    //     System.out.println("ID in transient state : " + employeeEntity.getId());
    //     this.entityManager.persist(employeeEntity);
    //     System.out.println("ID in Managed ( Before calling flush ) state : " + employeeEntity.getId());
    //     this.entityManager.flush();
    //     System.out.println("ID in Managed ( After calling flush ) state : " + employeeEntity.getId());
    //     EmployeeEntity employeeEntity2 = this.entityManager.find(EmployeeEntity.class, employeeEntity.getId());

    //     System.out.println("Samen ? " + (employeeEntity == employeeEntity2));

    //     employeeEntity.setDepartment("HR");
    //     return employeeEntity.toEmployee();
    // }

    // @Transactional
    // public void removeDemo() {
    //     EmployeeEntity employeeEntity = this.entityManager.find(EmployeeEntity.class, 1);
    //     this.entityManager.remove(employeeEntity);
    // }
    
    // @Transactional
    // public EmployeeEntity mergeDemo1() {
    //     return this.entityManager.find(EmployeeEntity.class, 1);
    // }

    // @Transactional
    // public void mergeDemo2(EmployeeEntity entity) {
    //     this.entityManager.merge(entity);
    //     entity.setName("Arjuna 2");
        
    // }
}
