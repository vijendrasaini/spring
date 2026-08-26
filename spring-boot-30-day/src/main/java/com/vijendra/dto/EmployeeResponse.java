package com.vijendra.dto;

import java.math.BigDecimal;

public class EmployeeResponse {
    public int id;
    public String name;
    public String email;
    public String department;
    public BigDecimal salary;

    public EmployeeResponse(
            int id,
            String name,
            String email,
            String department,
            BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }
}
