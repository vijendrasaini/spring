package com.vijendra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijendra.entity.EmployeeEntity;

public  interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{
}