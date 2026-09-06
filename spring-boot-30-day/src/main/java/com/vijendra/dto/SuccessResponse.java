package com.vijendra.dto;

import java.util.HashMap;
import java.util.Map;

public class SuccessResponse {
    private int status;
    private String message;
    private Map<String, Object> data;
    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = new HashMap<>();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
