package com.code.lms.service.impl;

import com.code.lms.model.UserPrincipal;
import com.code.lms.model.entity.User;
import com.code.lms.model.entity.UserEntity;
import com.code.lms.repository.TestUserRepo;
import com.code.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServicesImpl implements UserDetailsService {
    //To authenticate user To load user details from the database.
//    @Autowired
//    private TestUserRepo testUserRepo;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = testUserRepo.findByUserName(username);
//        if(user==null){
//            System.out.println("user Not found");
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        //a custom implementation of UserDetails
//        return new UserPrincipal(user);
 //   }
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        UserEntity user = userRepo.findByEmail(email);
        if(user==null){
            System.out.println("Email Not found");
            throw new UsernameNotFoundException("Email Email not found");
        }else{
            //a custom implementation of UserDetails
            System.out.println("User found: " + user.getEmail());
            UserPrincipal userPrincipal = new UserPrincipal(user);
            System.out.println("UserPrincipal created: " + userPrincipal.getUsername());
            return userPrincipal;
        }



        // return null;
    }
}
