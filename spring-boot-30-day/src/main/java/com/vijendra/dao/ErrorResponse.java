package com.vijendra.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private int status;
    private String message;
    private Map<String, List<String>> errors;
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.errors = new HashMap<>();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}
