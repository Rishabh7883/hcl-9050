package com.springCoreFundamentals.hrms;

import com.springCoreFundamentals.hrms.salaryPackage.Salary;

public class Employee {
    private String name;
    private Address address;
    private Department department;
    private Salary salary;

    public Employee(String name, Address address, Department department, Salary salary) {
        this.name = name;
        this.address = address;
        this.department = department;
        this.salary = salary;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address.getAddress());
        System.out.println("Department: " + department.getDepartment());
        System.out.println("Salary: " + salary.getSalary());
    }
}
