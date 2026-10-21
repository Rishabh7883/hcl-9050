package com.springCoreFundamentals.fieldDI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Employee {
    @Autowired
    private Address address;

    public Address getAddress() {
        return address;
    }
}
