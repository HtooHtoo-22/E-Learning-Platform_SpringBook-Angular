package com.code.lms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "resource")
public class ResourceEntity {
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
    private Integer id;

    @Column(name = "resource_type",nullable = false,length = 25)
    private String resourceType;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "link")
    private String link;

    @Column(name = "title",length = 20,nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    private UserEntity teacher;

    @ManyToOne
    @JoinColumn(name = "material_id",nullable = false)
    private MaterialEntity material;

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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public UserEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(UserEntity teacher) {
        this.teacher = teacher;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }
}
