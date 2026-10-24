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
                .filter(a -> employeeId == null || a.getEmployeeId().equals(employeeId))
                .filter(a -> date == null || a.getDate().equals(date))
                .toList();
    }

    public Attendance getById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Attendance checkIn(long employeeId) {
        if (employeeService.getById(employeeId) == null) {
            return null;
        }

        LocalDate today = LocalDate.now();

        boolean exists = repository.findAll().stream()
                .anyMatch(a -> a.getEmployeeId().equals(employeeId)
                        && a.getDate().equals(today));

        if (exists) {
            return null;
        }

        Attendance attendance = new Attendance(
                null, employeeId, today, LocalDateTime.now(), null
        );

        return repository.save(attendance);
    }

    public Attendance checkOut(long id) {
        Attendance attendance = getById(id);

        if (attendance == null || attendance.getCheckOut() != null) {
            return null;
        }

        attendance.setCheckOut(LocalDateTime.now());
        return repository.save(attendance);
    }

    public boolean delete(long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}