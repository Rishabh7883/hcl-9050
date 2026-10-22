package com.example.hrms.service;

import com.example.hrms.model.Employee;
import com.example.hrms.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(long id) {
        return repository.findById(id);
    }

    public Employee add(Employee employee) {
        return repository.save(employee);
    }

    public Employee update(long id, Employee updated) {
        Employee employee = repository.findById(id);

        if (employee == null) {
            return null;
        }

        employee.setName(updated.getName());
        employee.setEmail(updated.getEmail());
        employee.setDepartment(updated.getDepartment());

        return employee;
    }

    public boolean delete(long id) {
        if (repository.findById(id) == null) {
            return false;
        }

        repository.delete(id);
        return true;
    }
}
