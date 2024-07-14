package com.nandotoding.student_service.service;

import com.nandotoding.student_service.model.response.StudentResponse;

import java.util.List;

public interface StudentService {
    public List<StudentResponse> getAllStudents();
}
