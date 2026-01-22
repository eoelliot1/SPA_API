package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository,
                         CourseMapper courseMapper) {

        if (courseRepository == null || courseMapper == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .toList();
    }

    public CourseDTO getCourseById(int id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        return courseMapper.toDTO(course);
    }

    public CourseDTO saveCourse(CourseDTO courseDTO) {

        if (courseDTO.getCourseName() == null || courseDTO.getCourseName().isBlank()) {
            throw new IllegalArgumentException("Course name is required");
        }

        Course course = courseMapper.toEntity(courseDTO);
        return courseMapper.toDTO(courseRepository.save(course));
    }

    public CourseDTO updateCourse(int id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.setCourseName(courseDTO.getCourseName());
        return courseMapper.toDTO(courseRepository.save(course));
    }

    public void deleteCourse(int id) {
        if (!courseRepository.existsById(id)) {
            throw new NoSuchElementException("Course not found");
        }
        courseRepository.deleteById(id);
    }

    public List<Student> getStudentsByCourseId(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        return course.getStudents();
    }
}
