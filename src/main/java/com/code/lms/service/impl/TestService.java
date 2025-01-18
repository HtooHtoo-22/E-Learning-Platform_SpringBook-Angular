package com.code.lms.service.impl;

import com.code.lms.model.entity.User;
import com.code.lms.repository.TestUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestUserRepo testUserRepo;

    private BCryptPasswordEncoder passwordEncoderncoder = new BCryptPasswordEncoder(10);

    public User register(User user ){
        user.setPassword(passwordEncoderncoder.encode(user.getPassword()));
        testUserRepo.save(user);
        return user;
    }
}
