package com.sparta.spa_api.repository;

import com.sparta.spa_api.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByCourse_nameContainingIgnoreCase(String name);

}
