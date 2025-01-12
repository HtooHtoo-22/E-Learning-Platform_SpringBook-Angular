package com.code.lms.controller;

import com.code.lms.dto.UserDTO;
import com.code.lms.entity.UserEntity;
import com.code.lms.service.UserService;
import com.code.lms.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private UserService userService;

    private ApiResponse successResponse;
    @PostMapping("/createTeacher")
    public ResponseEntity<ApiResponse<?>> registerForTeacher(@RequestBody UserDTO teacher){
        userService.createTeacher(teacher);
         successResponse = ApiResponse.success(HttpStatus.CREATED,HttpStatus.CREATED.value(),"Created Teacher Successfully");
         return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
    }
    @PostMapping("/suspendUser/{userId}")
    public ResponseEntity<ApiResponse<?>> suspendTeacher(@PathVariable("userId")Integer userId){
        userService.suspendUser(userId);
        successResponse = ApiResponse.success(HttpStatus.OK,HttpStatus.OK.value(),"Suspended Successfully");
        return ResponseEntity.ok().body(successResponse);
    }
    @GetMapping("/getAllActiveTeachers")
    public List<UserDTO> getAllActiveTeachers(){
        return userService.getAllActiveTeachers();
    }
    @GetMapping("/getAllSuspendedTeachers")
    public List<UserDTO> getAllSuspendedTeachers(){
        return userService.getAllSuspendedTeachers();
    }
    @GetMapping("/getAllActiveStudents")
    public List<UserDTO> getAllActiveStudents(){
        return userService.getAllActiveStudents();
    }
    @GetMapping("/getAllSuspendedStudents")
    public List<UserDTO> getAllSuspendedStudents(){
        return userService.getAllSuspendedStudents();
    }
}
