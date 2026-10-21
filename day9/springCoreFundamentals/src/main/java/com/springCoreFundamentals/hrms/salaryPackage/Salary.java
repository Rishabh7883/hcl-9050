package com.springCoreFundamentals.hrms.salaryPackage;

public class Salary {
    BasicSalary basic;
    DaSalary da;
    HrSalary hr;

    public Salary(BasicSalary basic, HrSalary hr, DaSalary da) {
        this.basic = basic;
        this.hr = hr;
        this.da = da;
    }

    public int getSalary() {
        return basic.getBasic()+da.getDa()+hr.getHr();
    }
}
