package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appconfig.xml");

        OrderService bean = applicationContext.getBean("orderService", OrderService.class);
        bean.placeOrder();
    }
}
