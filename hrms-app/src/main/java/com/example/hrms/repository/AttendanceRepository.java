package com.example.hrms.repository;

import com.example.hrms.model.Attendance;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AttendanceRepository {
    private final List<Attendance> records = new ArrayList<>();
    private long nextId = 1;

    public List<Attendance> findAll() {
        return records;
    }

    public Attendance findById(long id) {
        return records.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Attendance save(Attendance attendance) {
        attendance.setId(nextId++);
        records.add(attendance);
        return attendance;
    }

    public void delete(long id) {
        records.removeIf(a -> a.getId() == id);
    }
}