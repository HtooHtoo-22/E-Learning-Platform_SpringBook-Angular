package com.code.lms.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name="user")
public class UserEntity {
    public enum Status {
        ACTIVE("Active"),
        SUSPENDED("Suspended");

        private final String displayName;
        Status(String displayName){
            this.displayName = displayName;
        }
        public String getDisplayName(){
            return displayName;
        }
    }
    public enum LoginStatus {
        LOGIN("Login"),
        UNLOGIN("Unlogin");

        private final String displayName;
        LoginStatus(String displayName){
            this.displayName = displayName;
        }
        public String getDisplayName(){
            return displayName;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name",length = 30,nullable = false)
    private String name;

    @Column(name="email",length = 50,nullable = false)
    private String email;

    @Column(name="password",length = 40,nullable = false)
    private String password;

    @Column(name="city",length = 30,nullable = false)
    private String city;

    @Column(name="gender",length = 10,nullable = false)
    private String gender;

    @Column(name="role",length = 10,nullable = false)
    private String role;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;


    @Enumerated(EnumType.STRING)
    @Column(name = "login_status",nullable = false)
    private LoginStatus loginStatus;


    @ManyToOne
    @JoinColumn(name = "admin_id")
    private UserEntity admin;

    @PrePersist
    protected void onCreate() {
        if (status == null) { // If not explicitly set, assign default
            status = Status.ACTIVE;
        }
        if(loginStatus == null){
            loginStatus = LoginStatus.LOGIN;
        }
        if(createdDate == null){
            LocalDateTime now = LocalDateTime.now();
            createdDate = now ;
        }
        if(role == null){
            role = "Student";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(   UserEntity admin) {
        this.admin = admin;
    }
}
