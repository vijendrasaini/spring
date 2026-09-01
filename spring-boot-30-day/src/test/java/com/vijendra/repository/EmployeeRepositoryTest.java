package com.vijendra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.vijendra.entity.EmployeeEntity;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void shouldSaveAndFindEmployeeByIdAndEmail() {
        // Arrage
        EmployeeEntity employeeEntity = new EmployeeEntity();

        String email = "krishna@gmail.com";
        String name = "Krishna H";
        employeeEntity.setName(name);
        employeeEntity.setEmail("krishna@gmail.com");
        employeeEntity.setSalary(new BigDecimal("100.89"));

        // Act
        EmployeeEntity saved = this.employeeRepository.save(employeeEntity);
        Optional<EmployeeEntity> found = this.employeeRepository.findById(saved.getId());
        found.orElseThrow();

        // Asset
        assertTrue(found.isPresent());
        assertEquals(name, found.get().getName());
        assertEquals(email, found.get().getEmail() + "s");
    }
}
