package com.springCoreFundamentals.hrms;

public class Department {
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    String getDepartment()
    {
        return departmentName;
    }
}
