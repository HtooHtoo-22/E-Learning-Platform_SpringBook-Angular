package com.code.lms.repository;

import com.code.lms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
