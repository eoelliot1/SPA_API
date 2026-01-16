package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    /**
     * Constructor-based dependency injection for CourseRepository.
     *
     * @param courseRepository the repository for course entities
     */

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        if(courseRepository == null || courseMapper == null){
            throw new IllegalArgumentException("Repository and Mapper cannot be null");
        }
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    /**
     * Retrieves all courses from the database.
     *
     * @return a list of all courses
     */

    public List<CourseDTO> getAllCourses() {


        return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
    }

    public CourseDTO getCourseById(int id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        return courseMapper.toDTO(course);
    }

    // Controller passes DTO; service maps + persists
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course entity = courseMapper.toEntity(courseDTO);
        Course saved = courseRepository.save(entity);
        return courseMapper.toDTO(saved);
    }

    public void deleteCourse(int id) {
        if (!courseRepository.existsById(id)) {
            throw new NoSuchElementException("Course not found");
        }
        courseRepository.deleteById(id);
    }



    public CourseDTO updateCourse(Integer courseId, CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));

        course.setCourseName(courseDTO.getCourseName());

        Course saved = courseRepository.save(course);
        return courseMapper.toDTO(saved);
    }

    public List<CourseDTO> searchCoursesByName(String name) {
        return courseRepository.findByCourseNameContainingIgnoreCase(name)
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

}