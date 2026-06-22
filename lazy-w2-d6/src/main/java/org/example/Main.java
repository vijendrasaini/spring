package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService bean = applicationContext.getBean(OrderService.class);
        bean.placeOrder();
    }
}
