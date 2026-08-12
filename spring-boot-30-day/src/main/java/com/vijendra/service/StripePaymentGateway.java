package com.vijendra.service;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{
    @Override
    public void pay() {
        System.out.println("Making the payment...");
    }
}
