package com.code.lms.service.impl;

import com.code.lms.dto.UserDTO;
import com.code.lms.entity.UserEntity;
import com.code.lms.repository.UserRepository;
import com.code.lms.service.UserService;
import com.code.lms.util.exception.InvalidEmailException;
import com.code.lms.util.mapper.UserMapper;
import com.code.lms.util.mapper.UserMapper1;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapper1 userMapper1;

    @Autowired
    private UserRepository userRepo;
    public void registerStudent(UserDTO student){
        UserEntity userWithSameEmail = userRepo.findByEmail(student.getEmail());
        if(userWithSameEmail != null){
            throw new InvalidEmailException("This Email is already used!");
        }
        UserEntity studentEntity = userMapper.toEntity(student);
        userRepo.save(studentEntity);
    }
    public void registerTeacher(UserDTO teacher){
        UserEntity userWithSameEmail = userRepo.findByEmail(teacher.getEmail());
        if(userWithSameEmail != null){
            throw new InvalidEmailException("This Email is already used!");
        }
        teacher.setRole("Teacher");
        teacher.setLoginStatus(UserEntity.LoginStatus.UNLOGIN);
        UserEntity teacherEntity = userMapper1.toEntity(teacher);

        userRepo.save(teacherEntity);
    }
}
