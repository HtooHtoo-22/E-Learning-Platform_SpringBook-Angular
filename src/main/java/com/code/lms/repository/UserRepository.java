package com.code.lms.repository;

import com.code.lms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByEmailAndPasswordAndStatus(String email , String password,UserEntity.Status status);
    UserEntity findByEmailAndStatus(String email , UserEntity.Status status);
    List<UserEntity> findByRoleAndStatus(String role , UserEntity.Status status);
}
