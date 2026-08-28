package com.vijendra.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijendra.entity.DepartmentEntity;
import com.vijendra.service.DepartmentService;

@RestController
@RequestMapping("/departments/")
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public DepartmentEntity getDepartment(@PathVariable int id) {
        DepartmentEntity departmentEntity = this.departmentService.getDepartment(id);
        if(departmentEntity == null) {
            throw new NoSuchElementException("Department with %d not found!".formatted(id));
        }

        return departmentEntity;
    }

    @PostMapping("/{name}")
    public DepartmentEntity createDepartment(@PathVariable String name) {
        return this.departmentService.create(name);
    }
}
