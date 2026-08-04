package com.vijendra;

import com.vijendra.model.Employee;
import com.vijendra.repository.EmployeeRepository;
import com.vijendra.service.EmployeeService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static EmployeeRepository employeeRepository = new EmployeeRepository();
    static EmployeeService employeeService = new EmployeeService(employeeRepository);
    static void main() {

/*        createEmployee();*/
        getEmployee(1);
    }

    public static void createEmployee() {
        // Create Emp
        Employee employee1 = new Employee("Ram", "ram@gmail.com");
        employee1.setDepartment("IT");
        employee1.setSalary(10000);

        Employee employeeResult = employeeService.create(employee1);
        System.out.println("Created employee : " + employeeResult);
    }

    public static void getEmployee(int id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee != null) {
            System.out.println("Employee ID : " + employee.getId());
            System.out.println("Employee Name : " + employee.getName());
            return;
        }

        System.out.println("No employee found with the give id : " + id);
    }
}
