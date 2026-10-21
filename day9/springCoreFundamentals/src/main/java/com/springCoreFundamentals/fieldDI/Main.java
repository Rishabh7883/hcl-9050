package com.springCoreFundamentals.fieldDI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee employee = (Employee) context.getBean(Employee.class);
        employee.getAddress().displayAddress();
    }
}
