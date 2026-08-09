package com.vijendra;

import com.vijendra.config.SpringJDBC;
import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import com.vijendra.dto.SpringJdbcData;
import com.vijendra.model.Employee;
import com.vijendra.service.EmployeeService;

import java.math.BigDecimal;
import java.util.List;

public class SpringJdbcDemo {
    static EmployeeService employeeService;
    static void main() {
        SpringJDBC springJDBC = new SpringJDBC();
        SpringJdbcData springJdbcData = springJDBC.getJdbcTemplate();

        EmployeeDAO employeeDAO = new EmployeeDAO(springJdbcData.jdbcTemplate, springJdbcData.transactionManager);
        employeeService = new EmployeeService(employeeDAO);

/*        showAllEmployees(getAllEmployees());*/
/*        createEmployee();*/
/*        getEmployee(1);*/
        update(28);
/*        delete(18);*/
/*        insertAndUpdate();*/
    }
    public static List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public static void showAllEmployees(List<Employee> employeeList) {
        int count = 1;
        for (Employee employee : employeeList) {
            showEmployee(employee);
        }
    }

    public static void showEmployee(Employee employee) {
        System.out.println("================ Employee - ID:" + employee.getId() + " Details===================");
        System.out.println("ID : " + employee.getId());
        System.out.println("Name : " + employee.getName());
        System.out.println("Email : " + employee.getEmail());
        System.out.println("Salary : " + employee.getSalary());
        System.out.println("Department : " + employee.getDepartment());
    }

    public static void createEmployee() {
        Employee employee = new Employee("Ramanand", "ramanad@gmail.com");
        employee.setDepartment("TV");
        employee.setSalary(new BigDecimal("85000.33"));

        int id = employeeService.create(employee);
        getEmployee(id);
    }

    public static void getEmployee(int id) {
        showEmployee(employeeService.getEmployee(id));
    }

    public static void update(int id) {
        if(employeeService.updateEmployeeName(id, "Gang-" + id)) {
            System.out.println("Updated successfully");
        }

        getEmployee(id);
    }

    public static void delete(int id) {
        if(employeeService.deleteEmployee(id)) {
            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Employee with id : " + id +" doesn't exist");
        }
    }

    public static void insertAndUpdate() {
        Employee employee = new Employee("Platform2", "platform2@gmail.com");
        employee.setDepartment("Test");
        boolean isCreated = employeeService.insertAndUpdate(employee, new BigDecimal("22.89"));
        if(isCreated) {
            System.out.println("Employee created successfully with salary updated");
            return;
        }

        System.out.println("Something went wrong!");
    }
}
