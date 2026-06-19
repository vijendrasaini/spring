package org.example;

import org.example.payment.CardPayment;
import org.example.payment.Payment;
import org.example.payment.UPIPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.smartcardio.Card;

@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    @Primary
    public UPIPayment createUPIPayment() {
        return new UPIPayment();
    }

    @Bean
    public CardPayment createCardPayment() {
        return new CardPayment();
    }

    @Bean
    public  OrderService createOrderService(Payment payment) {
        return new OrderService(payment);
    }
}
