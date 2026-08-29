package com.vijendra.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vijendra.entity.DepartmentEntity;
import com.vijendra.entity.EmployeeEntity;
import com.vijendra.model.Employee;
import com.vijendra.service.JPAEmployeeService;

@Component
public class JpaLearningRunner implements CommandLineRunner{
    private final JPAEmployeeService jpaEmployeeService;
    public JpaLearningRunner(JPAEmployeeService jpaEmployeeService) {
        this.jpaEmployeeService = jpaEmployeeService;
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

        DepartmentEntity departmentEntity = jpaEmployeeService.getDepartment(1);
        System.out.println(departmentEntity.getName());
        System.out.println("Employeed Count : " + departmentEntity.getEmployees().size());

        System.out.println("=== JPA Learning Runner end ===");
    }
}
