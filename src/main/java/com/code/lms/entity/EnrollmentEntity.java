package com.code.lms.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "enrollment")
public class EnrollmentEntity {

        public enum  Status{
            PENDING("Pending"),
            COMPLETED("Completed"),
            CANCELLED("Cancelled");
            private String displayName;
            Status(String displayName){
                this.displayName = displayName;
            }

            public String getDisplayName() {
                return displayName;
            }
        }

        @EmbeddedId
        private UserCoursePK id;

        @ManyToOne
        @MapsId("userId")
        @JoinColumn(name = "user_id")
        private UserEntity user;

        @ManyToOne
        @MapsId("courseId")
        @JoinColumn(name = "course_id")
        private CourseEntity course;

        @Column(name = "enrollment_position",nullable = false,length = 15)
        private String enrollmentPosition;

        @Column(name = "enrollment_date",nullable = false)
        private LocalDateTime enrollmentDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "status",nullable = false)
        private Status status;

        @PrePersist
        public void onCreate(){
            if(enrollmentDate == null){
                LocalDateTime now = LocalDateTime.now();
                enrollmentDate = now;
            }
            if(status == null){
                status = Status.PENDING;
            }
        }

        @Embeddable
        public static class UserCoursePK implements Serializable{
            private Integer courseId;
            private Integer userId;

            public Integer getCourseId() {
                return courseId;
            }

            public void setCourseId(Integer courseId) {
                this.courseId = courseId;
            }

            public Integer getUserId() {
                return userId;
            }

            public void setUserId(Integer userId) {
                this.userId = userId;
            }
            @Override // Recommended
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                UserCoursePK that = (UserCoursePK) o;
                return Objects.equals(courseId, that.courseId) && Objects.equals(userId, that.userId);
            }

            @Override // Recommended
            public int hashCode() {
                return Objects.hash(courseId, userId);
            }
        }

    public UserCoursePK getId() {
        return id;
    }

    public void setId(UserCoursePK id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getEnrollmentPosition() {
        return enrollmentPosition;
    }

    public void setEnrollmentPosition(String enrollmentPosition) {
        this.enrollmentPosition = enrollmentPosition;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
