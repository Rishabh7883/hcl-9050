package com.springCoreFundamentals.constructorDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee employee = (Employee) context.getBean(Employee.class);
        Address address = (Address) context.getBean(Address.class);
        employee.getAddress(address);
    }
}
