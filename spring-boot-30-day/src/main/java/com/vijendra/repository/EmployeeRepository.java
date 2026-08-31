package com.vijendra.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vijendra.dto.EmployeeDeptSummary;
import com.vijendra.entity.EmployeeEntity;

public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {
    List<EmployeeEntity> findByDepartment_Name(String name);

    List<EmployeeEntity> findByName(String name);

    List<EmployeeEntity> findBySalaryGreaterThan(BigDecimal salary);

    // List<EmployeeEntity> findByDep_Name(String name); // throws
    // QueryCreationException

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
    List<EmployeeEntity> findEmployeesByDepartmentNameAndSalaryAbove(@Param("deptName") String deptName,
            @Param("minSalary") BigDecimal minSal);

    // @EntityGraph(value = "Employee.withDepartment") // when using named entity
    // graph
    @EntityGraph(attributePaths = { "department" }) // when using direct entity graph
    Page<EmployeeEntity> findAll(Pageable pageable);

    // @Query(
    // value = "select * from employees e where e.salary > :minSalary",
    // nativeQuery = true
    // )
    // Page<EmployeeEntity> findBySalaryGreaterThanNative(@Param("minSalary")
    // BigDecimal minSalary, Pageable pageable);

    @Query(value = """
            SELECT e.*
            FROM employees e
            INNER JOIN departments d ON d.id = e.department_id
            WHERE d.name = :deptName AND e.salary > :minSalary
            """, nativeQuery = true)
    List<EmployeeEntity> findHighEarnersInDeptNative(
            @Param("deptName") String deptName,
            @Param("minSalary") BigDecimal minSalary);

    // Day 22 bonus — native JOIN + DTO projection (no lazy department, no N+1)
    @Query(value = """
            SELECT e.id AS id,
                   e.name AS name,
                   e.salary AS salary,
                   d.name AS deptName
            FROM employees e
            INNER JOIN departments d ON d.id = e.department_id
            WHERE d.name = :deptName AND e.salary > :minSalary
            """, nativeQuery = true)
    List<EmployeeDeptSummary> findHighEarnersInDeptSummary(
            @Param("deptName") String deptName,
            @Param("minSalary") BigDecimal minSalary);

    // Native + Page: do NOT add LIMIT/OFFSET — pass Pageable; Spring appends
    // pagination + count
    @Query(value = """
            SELECT e.id AS id,
                   e.name AS name,
                   e.salary AS salary,
                   d.name AS deptName
            FROM employees e
            INNER JOIN departments d ON d.id = e.department_id
            WHERE d.name = :deptName AND e.salary > :minSalary
            """, countQuery = """
            SELECT count(*)
            FROM employees e
            INNER JOIN departments d ON d.id = e.department_id
            WHERE d.name = :deptName AND e.salary > :minSalary
            """, nativeQuery = true)
    Page<EmployeeDeptSummary> findHighEarnersInDeptSummaryPage(
            @Param("deptName") String deptName,
            @Param("minSalary") BigDecimal minSalary,
            Pageable pageable);

    @Modifying
    @Query("UPDATE EmployeeEntity e SET e.salary = :salary WHERE e.department.name = :deptName")
    int updateSalaryByDepartment(String deptName, BigDecimal salary);

    @Modifying
    @Query("DELETE FROM EmployeeEntity e WHERE e.email = :email")
    int deleteByEmail(@Param("email") String email);

    Page<EmployeeEntity> findAll(Specification<EmployeeEntity> spec, Pageable pageable);
}