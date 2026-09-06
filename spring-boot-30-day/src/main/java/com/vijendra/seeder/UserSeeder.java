package com.vijendra.seeder;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vijendra.entity.AppUserEntity;
import com.vijendra.repository.AppUserRepository;

/**
 * UserSeeder
 */
@Component
public class UserSeeder implements CommandLineRunner {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String DEFAULT_PASSWORD = "1234";

    public UserSeeder(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String... args) {
        String[] emails = { "vijendra@example.com", "admin@example.com" };
        for (String email : emails) {
            Optional<AppUserEntity> found = this.appUserRepository.findByEmail(email);
            if (found.isEmpty()) {
                AppUserEntity appUserEntity = new AppUserEntity();

                appUserEntity.setEmail(email);
                appUserEntity.setPasswordHash(this.passwordEncoder.encode(DEFAULT_PASSWORD));
            }
        }
    }
}