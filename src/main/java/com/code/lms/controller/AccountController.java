package com.code.lms.controller;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.service.AccountService;
import com.code.lms.service.UserService;
import com.code.lms.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> doLogin(@RequestBody LoginDTO loginDTO) {
        UserDTO user = accountService.login(loginDTO);
        ApiResponse successResponse = ApiResponse.success(HttpStatus.OK, HttpStatus.OK.value(), "Login Successful", user);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    @PostMapping("/registerForStudent")
    public ResponseEntity<ApiResponse<?>> registerForStudent(@RequestBody UserDTO student){
        System.out.println(student);
        userService.registerStudent(student);
        ApiResponse successResponse = ApiResponse.success(HttpStatus.CREATED,HttpStatus.CREATED.value(), "Register Successful");
        return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
    }
}

