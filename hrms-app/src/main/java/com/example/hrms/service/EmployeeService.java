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
        return repository.findById(id).orElse(null);
    }

    public Employee add(Employee employee) {
        employee.setId(null);
        return repository.save(employee);
    }

    public Employee update(long id, Employee updated) {
        Employee employee = getById(id);

        if (employee == null) {
            return null;
        }

        employee.setName(updated.getName());
        employee.setEmail(updated.getEmail());
        employee.setDepartment(updated.getDepartment());

        return repository.save(employee);
    }

    public boolean delete(long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}