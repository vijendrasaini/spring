package com.vijendra.service;

import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import org.springframework.stereotype.Component;

public interface PaymentGateway {
    void pay(EmployeeDAO employeeDAO);
}
