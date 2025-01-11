package com.code.lms.service;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;

public interface AccountService {
    public String checkEmail(LoginDTO loginDTO);
    public UserDTO checkEmailAndPassword(LoginDTO loginDTO);
}
