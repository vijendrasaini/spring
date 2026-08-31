package com.vijendra.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateEmployeeRequest {
    // fields
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "must contain only letters, digits, and spaces")
    private String name;

    @Email
    private String email;

    @NotNull
    private BigDecimal salary;

    @Positive
    private long departmentId;

    // Getters
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

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
