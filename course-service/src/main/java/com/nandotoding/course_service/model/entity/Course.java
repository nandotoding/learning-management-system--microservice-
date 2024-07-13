package com.nandotoding.course_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    private String id;
    private String courseName;
    private boolean isDeleted;
}
