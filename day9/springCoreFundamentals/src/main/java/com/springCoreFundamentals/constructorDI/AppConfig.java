package com.springCoreFundamentals.constructorDI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Address Address() {
        return new Address();
    }
    @Bean
    public Employee Employee(Address address) {
        return new Employee(address);
    }
}
