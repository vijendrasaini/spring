package com.vijendra.jpa;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.vijendra.entity.EmployeeEntity;

public class EmployeeSpecs {
    public static Specification<EmployeeEntity> nameContains(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<EmployeeEntity> departmentName(String deptName) {
        return (root, query, cb) -> cb.equal(root.get("department").get("name"), deptName);
    }

    public static Specification<EmployeeEntity> salaryGreaterThan(BigDecimal minSalary) {
        return (root, query, cb) -> cb.greaterThan(root.get("salary"), minSalary);
    }
}
