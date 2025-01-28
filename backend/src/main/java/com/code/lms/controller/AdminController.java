package com.code.lms.controller;

import com.code.lms.dto.CourseDTO;
import com.code.lms.dto.UserDTO;
import com.code.lms.service.CourseService;
import com.code.lms.service.UserService;
import com.code.lms.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    private ApiResponse successResponse;
    @PostMapping("/teachers/create")
    public ResponseEntity<ApiResponse<?>> registerForTeacher(@RequestBody UserDTO teacher){
         userService.createTeacher(teacher);
         successResponse = ApiResponse.success(HttpStatus.CREATED,HttpStatus.CREATED.value(),"Created Teacher Successfully");
         return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
    }

    @GetMapping("/getAllActiveTeachers")
    public List<UserDTO> getAllActiveTeachers(){
        return userService.getAllActiveTeachers();
    }
    @GetMapping("/getAllSuspendedTeachers")
    public List<UserDTO> getAllSuspendedTeachers(){
        return userService.getAllSuspendedTeachers();
    }
    @GetMapping("/getAllActiveStudents")
    public List<UserDTO> getAllActiveStudents(){
        return userService.getAllActiveStudents();
    }
    @PostMapping("/createCourse")
    public CourseDTO createCourse(@RequestParam("file") MultipartFile file,
                                        @RequestPart("courseDTO")CourseDTO courseDTO) throws IOException {
        courseDTO.setFile(file);
        CourseDTO course =courseService.createCourse(courseDTO);
        System.out.println(courseDTO.getTitle()+courseDTO.getDescription()+courseDTO.getCost()+courseDTO.getAdminId());
        return course;
    }
    @GetMapping("/teachers/edit/{id}")
    public UserDTO getTeacherById(@PathVariable("id")Integer trId){
        UserDTO editTeacher = userService.getUserById(trId);
        return editTeacher;
    }
    @PutMapping("/teachers/update/{id}")
    public ApiResponse<UserDTO> updateTeacher(@PathVariable("id")Integer trId,@RequestBody UserDTO teacher){
        userService.updateUser(trId,teacher);
        successResponse =ApiResponse.success(HttpStatus.CREATED,HttpStatus.CREATED.value(),"Updated Teacher Successfully",teacher);
        System.out.println(trId+" 4And "+teacher.toString());
        return successResponse;
    }
    @PutMapping("/teachers/suspend/{id}")
    public ResponseEntity<ApiResponse<?>> suspendTeacher(@PathVariable("id")Integer trId){
        userService.suspendUser(trId);
        successResponse = ApiResponse.success(HttpStatus.OK,HttpStatus.OK.value(),"Suspended Successfully");
        return ResponseEntity.ok().body(successResponse);
    }

}
