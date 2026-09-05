package com.vijendra.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.vijendra.model.Employee;
import com.vijendra.service.EmployeeService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void shouldReturnEmployeeById() throws Exception {
        Employee employee = new Employee("Krishna H", "krishna@gmail.com");
        employee.setId(1);
        employee.setSalary(new BigDecimal("50000"));

        when(employeeService.getEmployee(1)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Krishna H"))
        .andExpect(jsonPath("$.email").value("krishna@gmail.com"));
    }
}
