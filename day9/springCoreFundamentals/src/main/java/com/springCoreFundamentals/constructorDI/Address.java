package com.springCoreFundamentals.constructorDI;

import org.springframework.context.annotation.Bean;

public class Address {
    @Bean
    void getAddress() {
        System.out.println("Address of Employee bean");
    }
}
