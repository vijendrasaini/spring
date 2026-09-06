package com.vijendra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijendra.dto.RegisterRequest;
import com.vijendra.service.DBUserDetailsService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final DBUserDetailsService dbUserDetailsService;
    public AuthController(DBUserDetailsService dbUserDetailsService) {
        this.dbUserDetailsService = dbUserDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        this.dbUserDetailsService.register(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
