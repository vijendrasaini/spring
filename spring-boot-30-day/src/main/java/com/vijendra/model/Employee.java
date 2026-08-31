package com.vijendra.model;

import java.math.BigDecimal;

public class Employee {

    private int id;

    private String name;
    private String email;
    private BigDecimal salary;
    private long departmentId;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}
