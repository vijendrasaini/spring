package com.vijendra.service;

import com.vijendra.config.SpringJDBC;
import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import com.vijendra.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Main {
    static EmployeeService employeeService;
    static void main() {
        SpringJDBC springJDBC = new SpringJDBC();
        JdbcTemplate jdbcTemplate = springJDBC.getJdbcTemplate();

        EmployeeDAO employeeDAO = new EmployeeDAO(jdbcTemplate);
        employeeService = new EmployeeService(employeeDAO);

        showAllEmployees(getAllEmployees());
    }
    public static List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public static void showAllEmployees(List<Employee> employeeList) {
        int count = 1;
        for (Employee employee : employeeList) {
            System.out.println("================ Employee - " + count + " Details===================");
            System.out.println("ID : " + employee.getId());
            System.out.println("Name : " + employee.getName());
            System.out.println("Email : " + employee.getEmail());
            System.out.println("Salary : " + employee.getSalary());
            System.out.println("Department : " + employee.getDepartment());
        }
    }
}
