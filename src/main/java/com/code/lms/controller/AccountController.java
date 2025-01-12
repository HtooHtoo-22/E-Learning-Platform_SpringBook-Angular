package com.code.lms.controller;

import com.code.lms.dto.ChangePasswordDTO;
import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.entity.UserEntity;
import com.code.lms.service.AccountService;
import com.code.lms.service.UserService;
import com.code.lms.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> doLogin(@RequestBody LoginDTO loginDTO) {
        String status = null;
        UserDTO user = accountService.login(loginDTO);
        if(user.isForcePasswordChange()){
            status = "Forced To Change Password";
        }
        ApiResponse successResponse = ApiResponse.success(HttpStatus.OK, HttpStatus.OK.value(), "Login Successful", user,status);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    @PostMapping("/changePasswordForTeacher/{id}")
    public ResponseEntity<ApiResponse<?>> changeTeacherPassowrd(@PathVariable("id")Integer id,
                                                                @RequestBody ChangePasswordDTO chgPasswrdDTO){
        UserDTO user = accountService.changePassword(id,chgPasswrdDTO.getNewPassword());
        ApiResponse successResponse = ApiResponse.success(HttpStatus.OK,HttpStatus.OK.value(),"Change Password Successfully");
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
    @PostMapping("/registerForStudent")
    public ResponseEntity<ApiResponse<?>> registerForStudent( @RequestBody UserDTO student){
        System.out.println(student);
        userService.registerStudent(student);
        ApiResponse successResponse = ApiResponse.success(HttpStatus.CREATED,HttpStatus.CREATED.value(), "Register Successful");
        return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
    }


}

