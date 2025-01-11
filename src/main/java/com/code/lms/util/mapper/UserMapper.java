package com.code.lms.util.mapper;

import com.code.lms.dto.UserDTO;
import com.code.lms.entity.UserEntity;
import com.code.lms.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepo;

    // Convert UserEntity to UserDTO
    public UserDTO toDTO(UserEntity userEntity) {
        if (userEntity == null) {
            return null; // or throw an exception, depending on your requirements
        }

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        // Custom mappings
        userDTO.setCreatedDate(userEntity.getCreatedDate() != null ?
                userEntity.getCreatedDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
        userDTO.setAdminId(userEntity.getAdmin() != null ? userEntity.getAdmin().getId() : null);
        userDTO.setAdminName(userEntity.getAdmin() != null ? userEntity.getAdmin().getName() : null);

        return userDTO;
    }

    // Convert UserDTO to UserEntity
    public UserEntity toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null; // or throw an exception, depending on your requirements
        }

        // Configure ModelMapper to skip adminId and adminName during mapping
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        // Map common fields using ModelMapper
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        if (userEntity.getStatus() == null) {
            userEntity.setStatus(UserEntity.Status.ACTIVE); // Default status
        }
        // Custom mapping for createdDate
        if (userDTO.getCreatedDate() != null) {
            try {
                userEntity.setCreatedDate(LocalDateTime.parse(userDTO.getCreatedDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format for createdDate. Expected format: yyyy-MM-dd'T'HH:mm:ss", e);
            }
        } else {
            // Set default createdDate if not provided
            userEntity.setCreatedDate(LocalDateTime.now());
        }

        // Custom mapping for admin
        if (userDTO.getAdminId() != null) {
            Optional<UserEntity> adminOptional = userRepo.findById(userDTO.getAdminId());
            if (adminOptional.isPresent()) {
                userEntity.setAdmin(adminOptional.get()); // Set the actual UserEntity, not the Optional
            } else {
                throw new IllegalArgumentException("Admin with ID " + userDTO.getAdminId() + " not found");
            }
        }

        // Set default values for status, loginStatus, and role if not provided


        return userEntity;
    }
}