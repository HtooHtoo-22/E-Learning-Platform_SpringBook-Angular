package com.code.lms.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "course_payment")
public class CoursePaymentEntity {

    public enum  Status{
        PENDING("Pending"),
        SUCCESSFUL("Successful"),
        FAILED("Fail");
        private String displayName;
        Status(String displayName){
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    @EmbeddedId
    private EnrollmentEntity.UserCoursePK id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "student_id")
    private UserEntity student;

    @Column(name = "transaction_url",nullable = false)
    private String transactionUrl;
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    @PrePersist
    protected void onCreate() {
        if (status == null) { // If not explicitly set, assign default
            status = Status.PENDING;
        }
    }


    public EnrollmentEntity.UserCoursePK getId() {
        return id;
    }

    public void setId(EnrollmentEntity.UserCoursePK id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public UserEntity getStudent() {
        return student;
    }

    public void setStudent(UserEntity student) {
        this.student = student;
    }

    public String getTransactionUrl() {
        return transactionUrl;
    }

    public void setTransactionUrl(String transactionUrl) {
        this.transactionUrl = transactionUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
