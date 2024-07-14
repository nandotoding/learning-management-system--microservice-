package com.nandotoding.student_service.controller;

import com.nandotoding.student_service.model.request.GetRequest;
import com.nandotoding.student_service.model.response.StudentResponse;
import com.nandotoding.student_service.model.response.SuccessResponse;
import com.nandotoding.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/list")
    public ResponseEntity getAllStudents(@RequestBody GetRequest request) {
        List<StudentResponse> studentResponseList = studentService.getAllStudents();
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), "success", studentResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
}
