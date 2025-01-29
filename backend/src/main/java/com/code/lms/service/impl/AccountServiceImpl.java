package com.code.lms.service.impl;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.model.entity.User;
import com.code.lms.model.entity.UserEntity;
import com.code.lms.repository.TestUserRepo;
import com.code.lms.repository.UserRepository;
import com.code.lms.service.AccountService;
import com.code.lms.util.exception.IncorrectPasswordException;
import com.code.lms.util.exception.InvalidEmailException;
import com.code.lms.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestUserRepo testUserRepo;

    @Autowired
    private UserMapper userMapper;

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
           // return jwtService.generateToken(user.getUserName());
            return null;
        return "fail";
    }
    public String login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            if(authentication.isAuthenticated()){
                UserEntity user = userRepo.findByEmail(loginDTO.getEmail());
                return jwtService.generateToken(user.getName(),user.getEmail(),user.getRole());
            }
           return null;
        } catch (BadCredentialsException e) {
            throw new IncorrectPasswordException("Wrong password Kwa!");
        }
    }


    //    public UserDTO login(LoginDTO loginDTO){
//        UserEntity user = userRepo.findByEmailAndStatus(loginDTO.getEmail(), UserEntity.Status.ACTIVE);
//        if(user == null){
//            throw new InvalidEmailException("Invalid Email");
//        }
//        // Find the user by email and password
//        UserEntity userEntity = userRepo.findByEmailAndPasswordAndStatus(loginDTO.getEmail(),loginDTO.getPassword(), UserEntity.Status.ACTIVE);
//
//        // Check if the user exists
//        if (userEntity == null) {
//            throw new IncorrectPasswordException("Incorrect password. Please check your credentials and try again.");
//        }
//        // Return the user DTO if found
//        return userMapper.toDTO(userEntity);
//    }
    public UserDTO changePassword(Integer id ,String password){
        UserEntity userEntity = userRepo.findById(id).orElse(null);
        userEntity.setPassword(password);
        userEntity.setLoginStatus(UserEntity.LoginStatus.LOGIN);
        userRepo.save(userEntity);
        return userMapper.toDTO(userEntity);
    }
}
