package com.code.lms.util.mapper;

import com.code.lms.dto.UserDTO;
import com.code.lms.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Convert UserEntity to UserDTO
    public UserDTO toDTO(UserEntity userEntity) {
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
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        // Custom mappings
        if (userDTO.getCreatedDate() != null) {
            userEntity.setCreatedDate(LocalDateTime.parse(userDTO.getCreatedDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        return userEntity;
    }
}