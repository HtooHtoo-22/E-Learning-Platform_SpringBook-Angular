package com.code.lms.controller;

import com.code.lms.dto.UserDTO;
import com.code.lms.model.entity.User;
import com.code.lms.service.UserService;
import com.code.lms.service.impl.TestService;
import com.code.lms.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;
    private ApiResponse successResponse;
    @GetMapping("/")
    public String greet(HttpServletRequest request){
        return "Hello Wrold : "+request.getSession().getId();
    }
    @GetMapping("/students")
    public List<UserDTO> getAllActiveStudents(){
        return userService.getAllActiveStudents();
    }
    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/createTeacher")
    public ResponseEntity<ApiResponse<?>> registerForTeacher(@RequestBody UserDTO teacher){
        userService.createTeacher(teacher);
        successResponse = ApiResponse.success(HttpStatus.CREATED,HttpStatus.CREATED.value(),"Created Teacher Successfully");
        return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
    }
    @PostMapping("/testRegister")
    public User register(@RequestBody User user){
        return testService.register(user);
    }
}
