package com.vijendra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("vijendra")
                .password(passwordEncoder.encode("vijendra@1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin@1234"))
                .roles("USER", "ADMIN")
                .build();

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user, admin);
        // UserDetails userDetails = userDetailsService.loadUserByUsername("vijendra");
        // // just for my experiment

        return userDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.GET, "/employees", "/employees/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/employees").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/employees/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/actuator/health").permitAll()
                    .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
