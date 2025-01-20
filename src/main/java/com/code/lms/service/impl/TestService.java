package com.code.lms.service.impl;

import com.code.lms.model.entity.User;
import com.code.lms.repository.TestUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestUserRepo testUserRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder passwordEncoderncoder = new BCryptPasswordEncoder(10);

    public User register(User user ){
        user.setPassword(passwordEncoderncoder.encode(user.getPassword()));
        testUserRepo.save(user);
        return user;
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken
                (user.getUserName(),user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUserName());
        return "fail";
    }
}
