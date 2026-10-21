package com.springcorefundamentals.springbootfundamentals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/")
    public String welcome(){
        return "Welocome to web app";
    }
}
