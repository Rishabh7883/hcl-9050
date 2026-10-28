package com.example.hrms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hrms.model.Employee;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeService service,
                              EmployeeRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id) {
        Employee employee = service.getById(id);

        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(employee));
    }

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Employee employee) {

        Optional<Employee> result =
                repository.findByEmail(employee.getEmail());

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Employee existing = result.get();

        if (employee.getPassword() == null ||
                !employee.getPassword().equals(existing.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(existing);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(
            @PathVariable long id,
            @RequestBody Employee employee) {

        Employee updated = service.update(id, employee);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        if (!service.delete(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}