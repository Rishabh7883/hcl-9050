package com.example.hrms.repository;

import com.example.hrms.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 1;

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Employee save(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    public void delete(long id) {
        employees.removeIf(e -> e.getId() == id);
    }
}