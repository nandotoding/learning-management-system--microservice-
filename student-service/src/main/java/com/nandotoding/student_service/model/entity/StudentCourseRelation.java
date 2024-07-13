package com.nandotoding.student_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "students_courses_relation")
@Data
public class StudentCourseRelation {
    @Id
    private String id;
    private String studentId;
    private String courseId;
}
