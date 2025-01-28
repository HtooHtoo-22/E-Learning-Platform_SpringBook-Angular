package com.code.lms.service;

import com.code.lms.dto.UserDTO;
import com.code.lms.util.ApiResponse;

import java.util.List;

public interface UserService {
    public void registerStudent(UserDTO student);
    public void createTeacher(UserDTO teacher);
    public void suspendUser(Integer userId);
    public List<UserDTO> getAllActiveTeachers();
    public List<UserDTO> getAllSuspendedTeachers();
    public List<UserDTO> getAllActiveStudents();
    public List<UserDTO> getAllSuspendedStudents();
    public UserDTO getUserById(Integer id);
    public UserDTO updateUser(Integer userId ,UserDTO userDTO);
}
