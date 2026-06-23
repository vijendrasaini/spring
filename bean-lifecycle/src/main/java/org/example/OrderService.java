package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

// @Component
public class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        System.out.println("OrderService Constructor");
        this.paymentService = paymentService;
    }

    public void placeOrder() {

        paymentService.pay();
        System.out.println("+Order placed");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Post-Construct method called");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy called");
    }

    public void initMethod() {
        System.out.println("Inside init method");
    }

    public void destroyMethod() {
        System.out.println("Inside my destroy method");
    }
}
