package com.sparta.spa_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByStudentName(String studentName);
    Student findByCourseId(Integer coursesId);
    boolean hasGraduated(boolean hasGraduated);

    List<Student> courseId(Integer courseId);
}
