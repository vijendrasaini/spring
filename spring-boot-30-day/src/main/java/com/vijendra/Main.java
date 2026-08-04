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

/*        createEmployee("Ram", "ram@gmail.com", "IT", 10000.00);*/
/*        createEmployee("Laxman", "laxman@gmail.com", "HR", 20000.00);*/
/*        createEmployee("Hanuma", "hanumal@gmail.com", "Sales", 50000.50);*/
        /*        getEmployee(1);*/
        deleteEmployee(5); // delete Hanuman
    }
    public static void deleteEmployee(int id) {
        boolean deleteResult = employeeService.deleteEmployee(id);
        if(deleteResult) {
            System.out.println("Employee with id : " + id + " has been deleted");
            return;
        }

        System.out.println("Employee with Id " + id + " couldn't be deleted");
    }
    public static void createEmployee(String name, String email, String department, double salary) {
        // Create Emp
        Employee employee1 = new Employee(name, email);
        employee1.setDepartment(department);
        employee1.setSalary(salary);

        Employee employeeResult = employeeService.create(employee1);
        if(employeeResult != null) {
            System.out.println("Created employee name: " + employeeResult.getName());
            return;
        }

        System.out.println("Something is wronge.");
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
