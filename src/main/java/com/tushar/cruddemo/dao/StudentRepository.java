package com.tushar.cruddemo.dao;

import com.tushar.cruddemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // add a method to sort by last name

    public List<Student> findAllByOrderByLastNameAsc();
}
