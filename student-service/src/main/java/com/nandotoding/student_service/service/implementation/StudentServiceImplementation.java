package com.nandotoding.student_service.service.implementation;

import com.nandotoding.student_service.model.entity.Student;
import com.nandotoding.student_service.model.response.StudentResponse;
import com.nandotoding.student_service.repository.StudentRepository;
import com.nandotoding.student_service.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        List<StudentResponse> studentResponseList = new ArrayList<>();
        List<Student> studentList = studentRepository.getAllStudents();

        for (Student s : studentList) {
            studentResponseList.add(new StudentResponse(s.getId(), s.getName()));
        }

        return studentResponseList;
    }
}
