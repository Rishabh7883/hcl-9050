package com.springcorefundamentals.springbootfundamentals.controller;

import com.springcorefundamentals.springbootfundamentals.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private Employee demoEmployee;

    @GetMapping("/")
    public String Welcome() {
        return "Hello World!";
    }

    @GetMapping("/add")
    public int add() {
        return demoEmployee.add(1, 5);
    }

    @GetMapping("/sqrt")
    public double sqrt() {
        return demoEmployee.squareRoot(64);
    }

    @GetMapping("/gtn")
    public double gtn() {
        return demoEmployee.gtn(7,56);
    }
}
