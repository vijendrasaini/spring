package com.vijendra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @Size(max = 100)
    @Email
    private String email;

    @Size(min = 8, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "must contain only letters, digits")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
