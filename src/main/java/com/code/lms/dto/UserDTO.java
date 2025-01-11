package com.code.lms.dto;

import com.code.lms.entity.UserEntity;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String city;
    private String gender;
    private String role;
    private String createdDate;
    private UserEntity.Status status;
    private UserEntity.LoginStatus loginStatus;
    private Integer adminId;
    private String adminName;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity.Status getStatus() {
        return status;
    }

    public void setStatus(UserEntity.Status status) {
        this.status = status;
    }

    public UserEntity.LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(UserEntity.LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
