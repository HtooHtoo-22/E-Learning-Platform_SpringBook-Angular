package com.code.lms.service;

import com.code.lms.dto.CourseDTO;

import java.io.IOException;

public interface CourseService {
    public CourseDTO createCourse(CourseDTO courseDTO) throws IOException;
}
