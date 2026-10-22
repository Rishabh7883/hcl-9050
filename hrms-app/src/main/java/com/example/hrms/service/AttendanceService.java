package com.example.hrms.service;

import com.example.hrms.model.Attendance;
import com.example.hrms.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository repository;
    private final EmployeeService employeeService;

    public AttendanceService(AttendanceRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    public List<Attendance> getAll(Long employeeId, LocalDate date) {
        return repository.findAll().stream()
                .filter(a -> employeeId == null || a.getEmployeeId() == employeeId)
                .filter(a -> date == null || a.getDate().equals(date))
                .toList();
    }

    public Attendance getById(long id) {
        return repository.findById(id);
    }

    public Attendance checkIn(long employeeId) {
        if (employeeService.getById(employeeId) == null) {
            return null;
        }

        boolean exists = repository.findAll().stream()
                .anyMatch(a -> a.getEmployeeId() == employeeId
                        && a.getDate().equals(LocalDate.now()));

        if (exists) {
            return null;
        }

        Attendance attendance = new Attendance(
                0, employeeId, LocalDate.now(), LocalDateTime.now(), null
        );

        return repository.save(attendance);
    }

    public Attendance checkOut(long id) {
        Attendance attendance = repository.findById(id);

        if (attendance == null || attendance.getCheckOut() != null) {
            return null;
        }

        attendance.setCheckOut(LocalDateTime.now());
        return attendance;
    }

    public boolean delete(long id) {
        if (repository.findById(id) == null) {
            return false;
        }

        repository.delete(id);
        return true;
    }
}