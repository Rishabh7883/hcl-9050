package com.StudentEnrollment;

public class StudentService{
    private Student student;

    public StudentService(Student student){
        this.student = student;
    }

    public void displayStudentProfile(){
        student.displayStudent();
    }
}