package com.vijendra.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vijendra.entity.DepartmentEntity;
import com.vijendra.entity.EmployeeEntity;
import com.vijendra.model.Employee;
import com.vijendra.repository.EmployeeRepository;
import com.vijendra.service.JPAEmployeeService;

@Component
public class JpaLearningRunner implements CommandLineRunner{
    private final JPAEmployeeService jpaEmployeeService;
    private final EmployeeRepository employeeRepository;
    public JpaLearningRunner(JPAEmployeeService jpaEmployeeService, EmployeeRepository employeeRepository) {
        this.jpaEmployeeService = jpaEmployeeService;
        this.employeeRepository = employeeRepository;
    }

    public void run(String... args) {
        System.out.println("=== JPA Learning Runner start ===");
        
        // EmployeeEntity employeeEntity = jpaEmployeeService.mergeDemo1();
        // System.out.println("Name 1 : " + employeeEntity.getName());
        // employeeEntity.setName("Krishna");
        // EmployeeEntity employeeEntity2 = jpaEmployeeService.mergeDemo1(); // name should "Arjun"
        // System.out.println("Name 2 : " + employeeEntity2.getName());
        // jpaEmployeeService.mergeDemo2(employeeEntity);
        // EmployeeEntity employeeEntity3 = jpaEmployeeService.mergeDemo1(); // name should be "krishna";
        // System.out.println("Name 3 : " + employeeEntity3.getName());

        
        // this.jpaEmployeeService.removeDemo();
        
        // EmployeeEntity employeeEntity = jpaEmployeeService.getEmployee(9);
        // System.out.println(employeeEntity.getDepartment().getName()); // it thrown exception

        // System.out.println("Department Name : " + this.jpaEmployeeService.getDepartment(9));

        // DepartmentEntity departmentEntity = jpaEmployeeService.getDepartment(1);
        // System.out.println(departmentEntity.getName());
        // System.out.println("Employeed Count : " + departmentEntity.getEmployees().size());

        // Spring Data JPA : Derive Query Method
        // List<EmployeeEntity> employeeEntities = this.employeeRepository.findByDepartment_Name("IT");
        // System.out.println("Employees by Department : " + "IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities = this.employeeRepository.findByName("Test");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities = this.employeeRepository.findBySalaryGreaterThan(new BigDecimal("200.00"));
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");


        // List<EmployeeEntity> employeeEntities = this.employeeRepository.findByNameContainingIgnoreCase("test");
        // System.out.println("Employees by Department : " + "IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities = this.employeeRepository.findByDepartment_NameAndSalaryGreaterThan("IT", new BigDecimal("200.00"));
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");

        // employeeEntities = this.employeeRepository.findByDepartment_NameOrderBySalaryDesc("IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));
        // System.out.println("______________________________________");


        EmployeeEntity employeeEntities = this.employeeRepository.findByEmail("vijendra@gmail.com").get();
        System.out.println(employeeEntities.getName());
        System.out.println("______________________________________");

        System.out.println("FOUND ? " + this.employeeRepository.existsByEmail("vijendra@gmail.com"));

        // employeeEntities = this.employeeRepository.findByDep_Name("IT");
        // employeeEntities.stream().forEach(ee -> System.out.println(ee.getName()));

        System.out.println("=== JPA Learning Runner end ===");
    }
}
