package com.tushar.cruddemo.controller;

import com.tushar.cruddemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentRestController {
    List<Student> students;

    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();
        students.add(new Student("FirstName", "LastName", "FirstLast@email.com"));
        students.add(new Student("SecondName", "LastName", "SecondLast@email.com"));
        students.add(new Student("ThirdName", "LastName", "ThirdLast@email.com"));

    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if((studentId >= students.size()) || studentId<0)
            throw new StudentNotFoundException("Student id not found - " + studentId);
        return students.get(studentId);
    }
    // ADd an exception handler using @Exception Handler


}





