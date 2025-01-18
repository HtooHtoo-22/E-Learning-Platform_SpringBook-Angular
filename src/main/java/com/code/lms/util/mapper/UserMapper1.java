package com.code.lms.util.mapper;

import com.code.lms.dto.UserDTO;
import com.code.lms.model.entity.UserEntity;
import com.code.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper1 {
    @Autowired
    private UserRepository userRepo;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static UserDTO toDTO(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        UserDTO dto = new UserDTO();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword()); // Consider not mapping passwords for security
        dto.setCity(entity.getCity());
        dto.setGender(entity.getGender());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate() != null ? entity.getCreatedDate().format(DATE_FORMATTER) : null);
        dto.setStatus(entity.getStatus());
        dto.setLoginStatus(entity.getLoginStatus());

        if (entity.getAdmin() != null) {
            dto.setAdminId(entity.getAdmin().getId());
            dto.setAdminName(entity.getAdmin().getName());
        }

        return dto;
    }

    // Convert UserDTO to UserEntity
    public  UserEntity toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setCity(dto.getCity());
        entity.setGender(dto.getGender());
        entity.setRole(dto.getRole());
        entity.setStatus(dto.getStatus());
        entity.setLoginStatus(dto.getLoginStatus());

        if (dto.getCreatedDate() != null) {
            entity.setCreatedDate(LocalDateTime.parse(dto.getCreatedDate(), DATE_FORMATTER));
        }

        if (dto.getAdminId() != null) {
            UserEntity admin = userRepo.findById(dto.getAdminId()).orElse(null);
            entity.setAdmin(admin);
        }

        return entity;
    }
    public  List<UserDTO> toDTOList(List<UserEntity> entities) {
        if (entities == null) {
            return null;
        }

        // Create a new list to store the DTOs
        List<UserDTO> dtoList = new ArrayList<>();

        // Iterate over the entities and convert each one to a DTO
        for (UserEntity entity : entities) {
            UserDTO dto = toDTO(entity);
            if (dto != null) {
                dtoList.add(dto);
            }
        }

        return dtoList;
    }
}
