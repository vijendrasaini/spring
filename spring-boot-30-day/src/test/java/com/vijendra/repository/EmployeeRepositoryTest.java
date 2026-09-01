package com.vijendra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.vijendra.entity.DepartmentEntity;
import com.vijendra.entity.EmployeeEntity;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManager entityManager;

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
        assertEquals(email, found.get().getEmail());
    }

    @Test
    void shouldFindEmployeeByEmail() {
        // Arrange
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName("Krishna H");
        employee.setEmail("krishna@gmail.com");
        employee.setSalary(new BigDecimal("100.89"));
        employeeRepository.save(employee);
        // Act
        Optional<EmployeeEntity> found = employeeRepository.findByEmail("krishna@gmail.com");
        // Assert
        assertTrue(found.isPresent());
        assertEquals("Krishna H", found.get().getName());
        assertEquals("krishna@gmail.com", found.get().getEmail());
    }

    @Test
    void shouldFindEmployeesByDepartmentAndMinSalary_usingJpqlQuery() {
        // Arrange — department
        DepartmentEntity itDept = new DepartmentEntity();
        itDept.setName("IT");
        itDept.setLocation("Bangalore");
        entityManager.persist(itDept);

        // Arrange — matching employee (IT, salary > 100)
        EmployeeEntity highEarner = new EmployeeEntity();
        highEarner.setName("Vijendra");
        highEarner.setEmail("vijendra@test.in");
        highEarner.setSalary(new BigDecimal("50000"));
        highEarner.setDepartment(itDept);
        entityManager.persist(highEarner);

        // Arrange — non-matching employee (low salary)
        EmployeeEntity lowEarner = new EmployeeEntity();
        lowEarner.setName("Intern");
        lowEarner.setEmail("intern@test.in");
        lowEarner.setSalary(new BigDecimal("50"));
        lowEarner.setDepartment(itDept);
        entityManager.persist(lowEarner);

        entityManager.flush(); // push to DB before query

        // Act — your custom @Query JPQL method
        List<EmployeeEntity> result = employeeRepository
                .findEmployeesByDepartmentNameAndSalaryAbove("IT", new BigDecimal("100"));

        // Assert
        assertEquals(1, result.size());
        assertEquals("Vijendra", result.get(0).getName());
        assertEquals("vijendra@test.in", result.get(0).getEmail());
    }
}
