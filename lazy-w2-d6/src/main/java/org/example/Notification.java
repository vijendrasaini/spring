package org.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Notification {
    private final OrderService orderService;

    public Notification(OrderService orderService) {
        System.out.println("Notification Constructor");
        this.orderService = orderService;
    }
    public void send() {
        System.out.println("Notification is sent");
    }
}
