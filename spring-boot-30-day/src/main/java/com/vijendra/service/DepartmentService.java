package com.vijendra.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vijendra.entity.DepartmentEntity;

import jakarta.persistence.EntityManager;

@Service
public class DepartmentService {
    private final EntityManager entityManager;
    public DepartmentService(EntityManager em) {
        this.entityManager = em;
    }

    @Transactional
    public DepartmentEntity create(String name) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName(name);
        this.entityManager.persist(departmentEntity);
        this.entityManager.flush();
        return departmentEntity;
    }

    @Transactional
    public DepartmentEntity getDepartment(int id) {
        return this.entityManager.find(DepartmentEntity.class, id);
    }
}
