package com.code.lms.controller;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.service.AccountService;
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
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> doLogin(@RequestBody LoginDTO loginDTO){
        String message = accountService.checkEmail(loginDTO);
        if(message!=null){
            ApiResponse errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST,400,message);
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }else{
            UserDTO userDTO = accountService.checkEmailAndPassword(loginDTO);
            if(userDTO == null){
                message = "Incorrect Password";
                ApiResponse errorResponse = ApiResponse.error(HttpStatus.BAD_REQUEST,400,message);
                return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
            }else{
                message = "Login Successful";
                ApiResponse successResponse = ApiResponse.success(HttpStatus.OK,200,message,userDTO);
                return new ResponseEntity<>(successResponse,HttpStatus.OK);
            }

        }
    }
}
