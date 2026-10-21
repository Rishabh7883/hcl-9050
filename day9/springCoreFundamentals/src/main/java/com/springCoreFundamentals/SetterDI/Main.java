package com.springCoreFundamentals.SetterDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee employee = (Employee) context.getBean(Employee.class);
        Employee employee1 = (Employee) context.getBean(Employee.class);
        System.out.println(employee.hashCode());
        System.out.println(employee1.hashCode());
    }

}
