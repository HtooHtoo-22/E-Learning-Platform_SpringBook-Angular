package com.code.lms.service.impl;

import com.code.lms.dto.UserDTO;
import com.code.lms.model.entity.UserEntity;
import com.code.lms.repository.UserRepository;
import com.code.lms.service.UserService;
import com.code.lms.util.ApiResponse;
import com.code.lms.util.etc.RandomCodeGenerator;
import com.code.lms.util.exception.InvalidEmailException;
import com.code.lms.util.exception.NotFoundException;
import com.code.lms.util.mapper.UserMapper;
import com.code.lms.util.mapper.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void createTeacher(UserDTO teacher){
        UserEntity userWithSameEmail = userRepo.findByEmail(teacher.getEmail());
        if(userWithSameEmail != null){
            throw new InvalidEmailException("This Email is already used!");
        }

        teacher.setRole("Teacher");
        teacher.setPassword(RandomCodeGenerator.generateCode());
        teacher.setLoginStatus("Unlogin");
        UserEntity teacherEntity = userMapper1.toEntity(teacher);

        userRepo.save(teacherEntity);
    }
    @Override
    public void suspendUser(Integer userId){
        UserEntity suspendedUser = userRepo.findById(userId).orElseThrow(()->new NotFoundException("User not found"));
        suspendedUser.setStatus(UserEntity.Status.SUSPENDED);
        userRepo.save(suspendedUser);
    }
    @Override
    public List<UserDTO> getAllActiveTeachers(){
        List<UserEntity> userEntityList = userRepo.findByRoleAndStatus("Teacher", UserEntity.Status.ACTIVE);
        if(userEntityList.isEmpty()){
            throw new NotFoundException("Active Teacher List is Empty");
        }
        List<UserDTO> userDTOList = userMapper1.toDTOList(userEntityList);
        return userDTOList;
    }
    @Override
    public List<UserDTO> getAllSuspendedTeachers(){
        List<UserEntity> userEntityList = userRepo.findByRoleAndStatus("Teacher", UserEntity.Status.SUSPENDED);
        if(userEntityList.isEmpty()){
            throw new NotFoundException("Suspended Teacher List is Empty");
        }
        List<UserDTO> userDTOList = userMapper1.toDTOList(userEntityList);
        return userDTOList;
    }
    @Override
    public List<UserDTO> getAllActiveStudents(){
        List<UserEntity> userEntityList = userRepo.findByRoleAndStatus("Student", UserEntity.Status.ACTIVE);
        if(userEntityList.isEmpty()){
            throw new NotFoundException("Active Student List is Empty");
        }
        List<UserDTO> userDTOList = userMapper1.toDTOList(userEntityList);
        return userDTOList;
    }
    @Override
    public List<UserDTO> getAllSuspendedStudents(){
        List<UserEntity> userEntityList = userRepo.findByRoleAndStatus("Student", UserEntity.Status.SUSPENDED);
        if(userEntityList.isEmpty()){
            throw new NotFoundException("Suspended Student List is Empty");
        }
        List<UserDTO> userDTOList = userMapper1.toDTOList(userEntityList);
        return userDTOList;
    }
    @Override
    public UserDTO getUserById(Integer id){
        UserEntity userEntity = userRepo.findById(id).orElseThrow(()->
                new NotFoundException("User Not Found Wit This ID :"+id));
        UserDTO userDTO = userMapper1.toDTO(userEntity);
        return userDTO;
    }
    @Override
    public UserDTO updateUser(Integer userId,UserDTO userDTO){
        UserEntity existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        existingUser.setName(userDTO.getName());
        existingUser.setCity(userDTO.getCity());
        existingUser.setGender(userDTO.getGender());
        userRepo.save(existingUser);
        return userDTO;
    }
}
