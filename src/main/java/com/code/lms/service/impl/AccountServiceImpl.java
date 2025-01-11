package com.code.lms.service.impl;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.entity.UserEntity;
import com.code.lms.repository.UserRepository;
import com.code.lms.service.AccountService;
import com.code.lms.util.exception.IncorrectPasswordException;
import com.code.lms.util.exception.InvalidEmailException;
import com.code.lms.util.mapper.UserMapper;
import lombok.extern.java.Log;
import org.apache.catalina.User;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper userMapper;

    public UserDTO login(LoginDTO loginDTO){
        UserEntity user = userRepo.findByEmail(loginDTO.getEmail());
        if(user == null){
            throw new InvalidEmailException("Invalid Email");
        }
        // Find the user by email and password
        UserEntity userEntity = userRepo.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());

        // Check if the user exists
        if (userEntity == null) {
            throw new IncorrectPasswordException("Incorrect password. Please check your credentials and try again.");
        }

        // Return the user DTO if found
        return userMapper.toDTO(userEntity);
    }
}
