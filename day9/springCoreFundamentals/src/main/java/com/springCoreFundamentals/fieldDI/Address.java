package com.springCoreFundamentals.fieldDI;

import org.springframework.stereotype.Component;

@Component
public class Address {
    private String address = "Crossing Republic";
    public void displayAddress() {
        System.out.println(address);
    }

}
