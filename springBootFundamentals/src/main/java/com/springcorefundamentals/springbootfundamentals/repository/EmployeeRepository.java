package com.springcorefundamentals.springbootfundamentals.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {
    public double squareroot(double d);

    public String empData();

    int add(int a, int b);

    int gtn(int a, int b);
}
