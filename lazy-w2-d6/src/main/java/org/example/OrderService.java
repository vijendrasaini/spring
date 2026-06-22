package org.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
public class OrderService {
    private final PaymentService paymentService;

    public OrderService(@Lazy PaymentService paymentService) {
        System.out.println("Order service Constructor");
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        System.out.println("Inside place order method");
        this.paymentService.pay();

        System.out.println("Order is placed");
    }
}
