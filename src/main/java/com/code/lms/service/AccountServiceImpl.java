package com.code.lms.service;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.entity.UserEntity;
import com.code.lms.repository.UserRepository;
import com.code.lms.util.mapper.UserMapper;
import lombok.extern.java.Log;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper userMapper;

    public String checkEmail(LoginDTO loginDTO){
        String message = null ;
        UserEntity user = userRepo.findByEmail(loginDTO.getEmail());
        if(user == null){
            message = "Invalid Email!";
        }
        return message;

    }
    public UserDTO checkEmailAndPassword(LoginDTO loginDTO){
        UserDTO userDTO = null;
        UserEntity userEntity = userRepo.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        if(userEntity!=null){
            userDTO=userMapper.toDTO(userEntity);
        }
        return userDTO;
    }

}
