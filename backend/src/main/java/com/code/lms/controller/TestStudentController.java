package com.code.lms.controller;

import com.code.lms.model.entity.TestStudent;
import com.code.lms.repository.TestStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin //!!!
@RequestMapping("/api/teststudents")
public class TestStudentController {

    @Autowired
    private TestStudentRepo testStudentRepo;

    @GetMapping
    public List<TestStudent> getAll(){
        List<TestStudent> students = testStudentRepo.findAll();
        System.out.println(students);
        return students;
    }
}
