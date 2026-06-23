package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public OrderService getOrder1(PaymentService paymentService) {
        System.out.println("inside get order 1 method");
        return new OrderService(paymentService);
    }
}
