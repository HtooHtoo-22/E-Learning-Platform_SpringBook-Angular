package com.code.lms.service;

import com.code.lms.dto.LoginDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.model.entity.User;

public interface AccountService {
    public String login(LoginDTO loginDTO);
    public UserDTO changePassword(Integer id ,String password);
    public com.code.lms.model.entity.User register(User user );
    public String verify(User user);

}
