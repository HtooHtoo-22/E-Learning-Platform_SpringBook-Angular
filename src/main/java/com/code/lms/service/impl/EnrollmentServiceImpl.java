package com.code.lms.service.impl;

import com.code.lms.repository.CourseRepository;
import com.code.lms.repository.UserRepository;
import com.code.lms.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void saveTeacherEnrollment(Integer teacherId , Integer courseId){

    }
}
