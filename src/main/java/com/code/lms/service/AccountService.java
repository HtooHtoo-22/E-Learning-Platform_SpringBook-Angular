package com.code.lms.service;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import org.apache.catalina.User;

public interface AccountService {
    public UserDTO login(LoginDTO loginDTO);
}
