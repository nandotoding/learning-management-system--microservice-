package com.nandotoding.student_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    private String id;
    private String name;
    private String credentialId;
    private boolean isDeleted;
}
