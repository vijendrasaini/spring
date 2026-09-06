package com.vijendra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijendra.entity.AppUserEntity;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long>{
    Optional<AppUserEntity> findByEmail(String email);
}
