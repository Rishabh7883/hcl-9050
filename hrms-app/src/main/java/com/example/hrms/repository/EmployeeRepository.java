package com.example.hrms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
}