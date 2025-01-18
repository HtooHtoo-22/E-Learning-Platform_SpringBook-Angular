package com.code.lms.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "material")
public class MaterialEntity {
    public enum  Status{
        PUBLISHED("Published"),
        DELETED("Deleted");
        private String displayName;
        Status(String displayName){
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id ;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "title",nullable = false,length = 30)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    private UserEntity teacher;

    @PrePersist
    public void onCreate(){
        if(status == null){
            status = Status.PUBLISHED;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public UserEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(UserEntity teacher) {
        this.teacher = teacher;
    }
}
