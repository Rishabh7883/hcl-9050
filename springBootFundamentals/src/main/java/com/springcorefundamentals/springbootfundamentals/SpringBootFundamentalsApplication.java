package com.springcorefundamentals.springbootfundamentals;

import com.springcorefundamentals.springbootfundamentals.controller.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootFundamentalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFundamentalsApplication.class, args);
        EmployeeController employeeController = new EmployeeController();
        employeeController.Welcome();
    }

}
