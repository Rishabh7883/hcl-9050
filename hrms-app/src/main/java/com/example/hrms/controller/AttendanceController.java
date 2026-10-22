package com.example.hrms.controller;

import com.example.hrms.model.Attendance;
import com.example.hrms.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {
    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Attendance> getAll(
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) LocalDate date) {
        return service.getAll(employeeId, date);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getById(@PathVariable long id) {
        Attendance attendance = service.getById(id);

        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(attendance);
    }

    @PostMapping("/check-in/{employeeId}")
    public ResponseEntity<Attendance> checkIn(@PathVariable long employeeId) {
        Attendance attendance = service.checkIn(employeeId);

        if (attendance == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
    }

    @PatchMapping("/{id}/check-out")
    public ResponseEntity<Attendance> checkOut(@PathVariable long id) {
        Attendance attendance = service.checkOut(id);

        if (attendance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(attendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (!service.delete(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}