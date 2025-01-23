package com.code.lms.controller;


import com.code.lms.model.entity.StudentTest;
import com.code.lms.model.entity.TestStudent;

import com.code.lms.repository.StudentTestRepo;
import com.code.lms.repository.TestStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //!!!
@RequestMapping("/api/teststudents")
public class TestStudentController {

    @Autowired
    private StudentTestRepo studentTestRepo;

    @GetMapping
    public List<StudentTest> getAll(){
        List<StudentTest> students = studentTestRepo.findAll();
        System.out.println(students);
        return students;
    }
    @PostMapping("/edit")
    public TestStudent edit(@PathVariable int id ,@RequestBody TestStudent student){
        StudentTest editStudent = studentTestRepo.findById(id).orElseThrow();
        editStudent.setName(student.getName());
        editStudent.setAge(student.getAge());
        editStudent.setEmail(student.getEmail());
        editStudent.setCourse(student.getCourse());
        studentTestRepo.save(editStudent);
        return student;
    }
}
