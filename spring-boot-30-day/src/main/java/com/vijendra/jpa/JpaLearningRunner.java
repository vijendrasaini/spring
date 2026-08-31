package com.vijendra.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.vijendra.dto.EmployeeDeptSummary;
import com.vijendra.repository.EmployeeRepository;
import com.vijendra.service.EmployeeService;
import com.vijendra.service.JPAEmployeeService;

@Component
public class JpaLearningRunner implements CommandLineRunner {
    private final JPAEmployeeService jpaEmployeeService;
    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public JpaLearningRunner(JPAEmployeeService jpaEmployeeService, EmployeeRepository employeeRepository,
            EmployeeService employeeService) {
        this.jpaEmployeeService = jpaEmployeeService;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    public void run(String... args) {
        System.out.println("=== JPA Learning Runner start ===");

        // EmployeeEntity employeeEntity = jpaEmployeeService.mergeDemo1();
        // System.out.println("Name 1 : " + employeeEntity.getName());
        // employeeEntity.setName("Krishna");
        // EmployeeEntity employeeEntity2 = jpaEmployeeService.mergeDemo1(); // name
        // should "Arjun"
        // System.out.println("Name 2 : " + employeeEntity2.getName());
        // jpaEmployeeService.mergeDemo2(employeeEntity);
        // EmployeeEntity employeeEntity3 = jpaEmployeeService.mergeDemo1(); // name
        // should be "krishna";
        // System.out.println("Name 3 : " + employeeEntity3.getName());

        // this.jpaEmployeeService.removeDemo();

        // EmployeeEntity employeeEntity = jpaEmployeeService.getEmployee(9);
        // System.out.println(employeeEntity.getDepartment().getName()); // it thrown
        // exception

        // System.out.println("Department Name : " +
        // this.jpaEmployeeService.getDepartment(9));

        // DepartmentEntity departmentEntity = jpaEmployeeService.getDepartment(1);
        // System.out.println(departmentEntity.getName());
        // System.out.println("Employeed Count : " +
        // departmentEntity.getEmployees().size());

        // Spring Data JPA : Derive Query Method
        // List<EmployeeEntity> employeeEntities =
        // this.employeeRepository.findByDepartment_Name("IT");
        // System.out.println("Employees by Department : " + "IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities = this.employeeRepository.findByName("Test");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities = this.employeeRepository.findBySalaryGreaterThan(new
        // BigDecimal("200.00"));
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // List<EmployeeEntity> employeeEntities =
        // this.employeeRepository.findByNameContainingIgnoreCase("test");
        // System.out.println("Employees by Department : " + "IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities =
        // this.employeeRepository.findByDepartment_NameAndSalaryGreaterThan("IT", new
        // BigDecimal("200.00"));
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities =
        // this.employeeRepository.findByDepartment_NameOrderBySalaryDesc("IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // EmployeeEntity employeeEntities =
        // this.employeeRepository.findByEmail("vijendra@gmail.com").get();
        // System.out.println(employeeEntities.getName());
        // System.out.println("______________________________________");

        // System.out.println("FOUND ? " +
        // this.employeeRepository.existsByEmail("vijendra@gmail.com"));

        // employeeEntities = this.employeeRepository.findByDep_Name("IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));

        // JPQL
        // List<EmployeeEntity> employeeEntities =
        // this.employeeRepository.findEmployeesByDepartmentNameAndSalaryAbove("IT", new
        // BigDecimal("200.00"));
        // employeeEntities.stream().forEach(ee -> System.out.println("Name : %s,
        // DeptName : %s".formatted(ee.getName(), ee.getDepartment().getName())));
        // System.out.println("______________________________________");

        // spring.jpa.open-in-view
        // Employee employee = this.employeeService.getEmployee(1);
        // System.out.println("Id : %d, Name : %s".formatted(employee.getId(),
        // employee.getName()));

        // Page<Employee> page = this.employeeService
        // .getEmployees(PageRequest.of(0, 2, Sort.by("name").descending()));
        // System.out.println("size: " + page.getSize());
        // System.out.println("Number : " + page.getNumber());
        // System.out.println("Total element : " + page.getTotalElements());
        // System.out.println("Total pages : " + page.getTotalPages());

        // System.out.println("==========data==========");
        // page.getContent().stream()
        // .forEach(ee -> System.out.println("ID : %d, Name : %s".formatted(ee.getId(),
        // ee.getName())));

        // Day 22 bonus — native DTO projection (1 query, dept name in result, no lazy load)
        System.out.println("--- native DTO list ---");
        employeeRepository.findHighEarnersInDeptSummary("IT", new BigDecimal("100.00"))
                .forEach(row -> System.out.println(
                        "%s | %s | dept=%s".formatted(row.getName(), row.getSalary(), row.getDeptName())));

        System.out.println("--- native DTO page (Pageable — no manual LIMIT/OFFSET) ---");
        Page<EmployeeDeptSummary> page = employeeRepository.findHighEarnersInDeptSummaryPage(
                "IT", new BigDecimal("100.00"), PageRequest.of(0, 2, Sort.by("salary").descending()));
        System.out.println("totalElements=" + page.getTotalElements() + ", totalPages=" + page.getTotalPages());
        page.getContent().forEach(row -> System.out.println(
                "%s | %s | dept=%s".formatted(row.getName(), row.getSalary(), row.getDeptName())));

        System.out.println("=== JPA Learning Runner end ===");
    }
}
