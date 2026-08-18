package com.vijendra.service;

import com.vijendra.dao.spring_jdbc.EmployeeDAO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Component
public class StripePaymentGateway implements PaymentGateway{

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void pay(EmployeeDAO employeeDAO) {
/*        System.out.println("Making the payment...");*/
        System.out.println("Updated name for User 4...");
        employeeDAO.updateByName(4, "Time4 " + LocalTime.now().plusSeconds(2));
/*        throw new RuntimeException("something wrong happened while updating user - 2");*/
    }
}
