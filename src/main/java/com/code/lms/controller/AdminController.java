package com.code.lms.controller;

import com.code.lms.dto.UserDTO;
import com.code.lms.service.UserService;
import com.code.lms.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @PostMapping("/registerForTeacher")
    public ResponseEntity<ApiResponse<?>> registerForTeacher(@RequestBody UserDTO teacher){
        System.out.println(teacher);
        userService.registerTeacher(teacher);
        ApiResponse successRespone = ApiResponse.success(HttpStatus.CREATED,HttpStatus.CREATED.value(),"Created Teacher Successfully");
        return new ResponseEntity<>(successRespone,HttpStatus.CREATED);
    }
}
