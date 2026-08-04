package org.example;

public class PaymentService {
    private String type;
    public PaymentService(String type) {
        this.type = type;
    }
    public void pay() {
        System.out.println("Payment via : " + this.type);
    }
}
