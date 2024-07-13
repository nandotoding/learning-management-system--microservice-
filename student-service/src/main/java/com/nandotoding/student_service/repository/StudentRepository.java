package com.nandotoding.student_service.repository;

import com.nandotoding.student_service.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query(value = "SELECT * FROM students s WHERE s.is_deleted = false", nativeQuery = true)
    public List<Student> getAllStudents();
}
