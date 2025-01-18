package com.code.lms.util.mapper;

import com.code.lms.dto.CourseDTO;
import com.code.lms.model.entity.CourseEntity;
import com.code.lms.model.entity.UserEntity;
import com.code.lms.repository.UserRepository;
import com.code.lms.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CourseMapper {

    @Autowired
    private UserRepository userRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public CourseDTO toDTO(CourseEntity entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImageUrl(entity.getImageUrl());
        dto.setPasscode(entity.getPasscode());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate().format(DATE_FORMATTER)); // Convert LocalDateTime to String
        dto.setCost(entity.getCost());
        dto.setAdminId(entity.getAdmin().getId()); // Set admin ID
        dto.setAdminName(entity.getAdmin().getName()); // Set admin name (assuming UserEntity has a 'name' field)
        return dto;
    }
    public  CourseEntity toEntity(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImageUrl(dto.getImageUrl());
        entity.setPasscode(dto.getPasscode());
        entity.setStatus(dto.getStatus());
        if(dto.getCreatedDate()!=null){
            entity.setCreatedDate(LocalDateTime.parse(dto.getCreatedDate(), DATE_FORMATTER)); // Convert String to LocalDateTime
        }

        entity.setCost(dto.getCost());
        UserEntity admin = userRepository.findById(dto.getAdminId()).orElseThrow(()->new NotFoundException("Not Found Admin"));
        entity.setAdmin(admin); // Set the admin UserEntity
        return entity;
    }
}
