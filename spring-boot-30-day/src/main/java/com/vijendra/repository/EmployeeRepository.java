package com.vijendra.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vijendra.entity.EmployeeEntity;

public  interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{
    List<EmployeeEntity> findByDepartment_Name(String name);
    
    List<EmployeeEntity> findByName(String name);
    
    List<EmployeeEntity> findBySalaryGreaterThan(BigDecimal salary);

    // List<EmployeeEntity> findByDep_Name(String name); // throws QueryCreationException

    List<EmployeeEntity> findByNameContainingIgnoreCase(String name);
    List<EmployeeEntity> findByDepartment_NameAndSalaryGreaterThan(String name, BigDecimal salary);
    List<EmployeeEntity> findByDepartment_NameOrderBySalaryDesc(String name);

    Optional<EmployeeEntity> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("""
            Select e from EmployeeEntity e
            Join Fetch e.department d
            Where d.name = :deptName
            And e.salary > :minSalary
            """)
    List<EmployeeEntity> findEmployeesByDepartmentNameAndSalaryAbove(@Param("deptName") String deptName, @Param("minSalary") BigDecimal minSal);
}