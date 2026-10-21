package com.springCoreFundamentals.constructorDI;

public class Employee {

    private Address address;
    public Employee(Address address) {
        this.address = address;
    }

    public void getAddress(Address address) {
        address.getAddress();
    }
}
