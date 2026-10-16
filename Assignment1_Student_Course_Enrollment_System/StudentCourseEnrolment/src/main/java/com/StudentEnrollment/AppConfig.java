package com.StudentEnrollment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course course(){
        return new Course("C01","Learn Java Full Stack",10);
    }

    @Bean
    public Student student(Course course){
        return new Student(1,"Rishabh Maurya",course);
    }

    @Bean
    public StudentService studentService(Student student){
        return new StudentService(student);
    }
}