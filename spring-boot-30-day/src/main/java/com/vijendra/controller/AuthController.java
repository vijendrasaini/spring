package com.vijendra.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijendra.dto.LoginRequest;
import com.vijendra.dto.RegisterRequest;
import com.vijendra.dto.SuccessResponse;
import com.vijendra.service.AuthService;
import com.vijendra.service.DBUserDetailsService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final DBUserDetailsService dbUserDetailsService;
    private final AuthService authService;
    public AuthController(DBUserDetailsService dbUserDetailsService, AuthService authService) {
        this.dbUserDetailsService = dbUserDetailsService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        this.dbUserDetailsService.register(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody LoginRequest request) {
        String jwtToken = this.authService.login(request);
        SuccessResponse response = new SuccessResponse(200, "");
        Map<String, Object> data = new HashMap<>();
        data.put("token", jwtToken);
        response.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
