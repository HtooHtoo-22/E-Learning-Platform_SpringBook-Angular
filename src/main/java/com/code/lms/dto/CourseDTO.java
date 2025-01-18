package com.code.lms.dto;

import com.code.lms.model.entity.CourseEntity;
import org.springframework.web.multipart.MultipartFile;

public class CourseDTO {
    private int id;
    private String title;
    private String description;
    private String imageUrl;
    private String passcode;
    private String createdDate;
    private int cost;
    private int adminId;
    private String adminName;
    private CourseEntity.Status status;
    private MultipartFile file;

    private Integer[] teacherIds;

    public Integer[] getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(Integer[] teacherIds) {
        this.teacherIds = teacherIds;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public CourseEntity.Status getStatus() {
        return status;
    }

    public void setStatus(CourseEntity.Status status) {
        this.status = status;
    }
}
