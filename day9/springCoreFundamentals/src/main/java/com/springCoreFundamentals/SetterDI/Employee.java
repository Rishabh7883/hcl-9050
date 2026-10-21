package com.springCoreFundamentals.SetterDI;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    public Employee() {
        System.out.println("Employee constructor");
    }
}
