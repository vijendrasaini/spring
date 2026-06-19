package org.example.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public class UPIPayment implements Payment{
    @Override
    public void pay() {
        System.out.println("Payment via UPI");
    }
}
