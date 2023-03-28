package com.tushar.cruddemo.controller;

import com.tushar.cruddemo.dao.StudentRepository;
import com.tushar.cruddemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/list")
    public String listStudent(Model theModel) {

        List<Student> theStudent = studentRepository.findAllByOrderByLastNameAsc();
        // add to the spring model
        theModel.addAttribute("student", theStudent);

        return "students/list-students";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        // create model attribute to bind form data
        Student theStudent = new Student();

        theModel.addAttribute("student", theStudent);
        return "students/student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student theStudent){

        // save the student
        studentRepository.save(theStudent);

        return "redirect:/student/list";

    }

    @GetMapping("/showFormForUpdate")
    public String StringshowFormForUpdate(@RequestParam("studentId") int theId, Model theModel){
        Optional<Student> theStudent = studentRepository.findById(theId);
        Student student = theStudent.get();
        theModel.addAttribute("student", student);
        return "students/student-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") int theId){
        Optional<Student> theStudent = studentRepository.findById(theId);
        Student student = theStudent.get();
        studentRepository.delete(student);
        return "redirect:/student/list";
    }

}
