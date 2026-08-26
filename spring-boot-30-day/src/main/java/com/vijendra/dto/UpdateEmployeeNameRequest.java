package com.vijendra.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateEmployeeNameRequest {
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "must contain only letters, digits, and spaces")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
