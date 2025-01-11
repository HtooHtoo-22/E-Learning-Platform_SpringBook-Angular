package com.code.lms.service;

import com.code.lms.dto.UserDTO;

public interface UserService {
    public void registerStudent(UserDTO student);
    public void registerTeacher(UserDTO teacher);
}
