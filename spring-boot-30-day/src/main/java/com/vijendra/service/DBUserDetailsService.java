package com.vijendra.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vijendra.dto.RegisterRequest;
import com.vijendra.entity.AppUserEntity;
import com.vijendra.exception.UserNameAlreadyExistsException;
import com.vijendra.repository.AppUserRepository;

@Service
public class DBUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DBUserDetailsService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
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

    public void register(RegisterRequest registerRequest) {
        String username = registerRequest.getEmail();
        boolean found = this.appUserRepository.existsByEmail(username.toLowerCase());
        if(found) {
            throw new UserNameAlreadyExistsException("%s already exists.".formatted(username));
        }

        AppUserEntity appUserEntity = new AppUserEntity();
        appUserEntity.setEmail(username.toLowerCase());
        appUserEntity.setPasswordHash(this.passwordEncoder.encode(registerRequest.getPassword()));
        appUserEntity.setEnabled(false);
        this.appUserRepository.save(appUserEntity);
    }

}
