package com.vijendra.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vijendra.entity.AppUserEntity;
import com.vijendra.repository.AppUserRepository;

@Service
public class DBUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public DBUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserEntity userEntity = this.appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPasswordHash())
                .disabled(!userEntity.getEnabled())
                .roles(userEntity.getRoles().split(","))
                .build();
    }

}
