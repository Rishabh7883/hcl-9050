package com.springcorefundamentals.springbootfundamentals.repository;

import org.springframework.stereotype.Component;

@Component
public class EmployeeRepositoryImp implements EmployeeRepository{
    @Override
    public double squareroot(double d){
        return Math.sqrt(d);
    }

    @Override
    public String empData() {
        return "";
    }

    @Override
    public int add(int a, int b){
        return a+b;
    }

    @Override
    public int gtn(int a, int b){
        if (a > b) return a;
        return b;
    }
}
