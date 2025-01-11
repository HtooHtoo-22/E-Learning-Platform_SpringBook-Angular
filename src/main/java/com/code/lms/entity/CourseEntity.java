package com.code.lms.entity;

import java.time.LocalDateTime;
import com.code.lms.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="course")
public class CourseEntity {

    public enum Status {
        OPENED("Opened"),
        CLOSED("Closed");

        private final String displayName;
        Status(String displayName){
            this.displayName = displayName;
        }
        public String getDisplayName(){
            return displayName;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title",nullable = false,length = 50)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "passcode",nullable = false,length = 6)
    private String passcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "cost",nullable = false)
    private int cost;
    @ManyToOne
    @JoinColumn(name="admin_id")
    private UserEntity admin;

    @PrePersist
    protected void onCreate() {
        if (status == null) { // If not explicitly set, assign default
            status = Status.OPENED ;
        }
        LocalDateTime now = LocalDateTime.now();
        if (createdDate == null) {
            createdDate = now;
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public UserEntity getAdmin() {
        return admin;
    }

    public void setAdmin(UserEntity admin) {
        this.admin = admin;
    }
}

