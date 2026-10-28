package com.example.hrms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hrms.model.Employee;
import com.example.hrms.repository.EmployeeRepository;

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

    public Optional<Employee> findByEmail(String email) {
        return repository.findByEmail(email);
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

        if (updated.getPassword() != null &&
                !updated.getPassword().isBlank()) {
            employee.setPassword(updated.getPassword());
        }

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