package org.example.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class CardPayment implements Payment{
    @Override
    public void pay() {
        System.out.println("Payment via CARD");
    }
}
