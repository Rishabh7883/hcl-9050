package com.springcorefundamentals.springbootfundamentals.model;

import com.springcorefundamentals.springbootfundamentals.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Employee {
    @Autowired
    private EmployeeRepository empRepo;

    public int add(int a, int b){
        return empRepo.add(a,b);
    }

    public double squareRoot(int a){
        return empRepo.squareroot(a);
    }

    public int gtn(int a, int b){
        return empRepo.gtn(a,b);
    }
}
