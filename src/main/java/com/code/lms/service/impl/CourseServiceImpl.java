package com.code.lms.service.impl;

import com.code.lms.dto.CourseDTO;
import com.code.lms.repository.CourseRepository;
import com.code.lms.service.CloudinaryService;
import com.code.lms.service.CourseService;
import com.code.lms.util.etc.RandomCodeGenerator;
import com.code.lms.util.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private  CloudinaryService cloudinaryService;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private CourseMapper courseMapper;
    public CourseDTO createCourse(CourseDTO courseDTO) throws IOException {
        String imageUrl = cloudinaryService.uploadFile(courseDTO.getFile());
        courseDTO.setImageUrl(imageUrl);
        courseDTO.setPasscode(RandomCodeGenerator.generateCode());
        courseRepo.save(courseMapper.toEntity(courseDTO));
        return courseDTO;
    }
}
