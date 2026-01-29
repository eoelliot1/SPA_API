package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.*;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    //Use for retrieval of course trainer
    private final TrainersMapper trainersMapper;

    public CourseService(CourseRepository courseRepository,
                         CourseMapper courseMapper,
                         TrainersMapper trainersMapper) {

        if (courseRepository == null || courseMapper == null || trainersMapper == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.trainersMapper = trainersMapper;
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

    public TrainersDTO getCourseTrainer(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Course not found"));


        Trainers courseTrainer = course.getTrainers().stream().findFirst().orElseThrow(() -> new NoSuchElementException("Course has no trainers..."));

        return trainersMapper.toDTO(courseTrainer);
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

    public List<CourseDTO> getCoursesLongerThan(long minDays) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getDurationInDays() >= minDays)
                .map(courseMapper::toDTO)
                .toList();
    }

    public List<CourseDTO> getCoursesByDurationRange(long minDays, long maxDays) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> {
                    long duration = course.getDurationInDays();
                    return duration >= minDays && duration <= maxDays;
                })
                .map(courseMapper::toDTO)
                .toList();
    }

    public List<CourseDTO> getActiveCoursesLongerThan(long minDays) {
        return courseRepository.findAll()
                .stream()
                .filter(course -> course.getEndDate() == null)
                .filter(course -> course.getDurationInDays() >= minDays)
                .map(courseMapper::toDTO)
                .toList();
    }
    public List<CourseDTO> searchCourses(String keyword) {
        return courseRepository
                .findByCourseNameContainingIgnoreCase(keyword)
                .stream()
                .map(courseMapper::toDTO)
                .toList();
    }
    public List<Course> getAllCourseEntities() {
        return courseRepository.findAll();
    }



    /*
        Parameters:
        Id: The trainer Id we will use to set the course
        Course: the course object

        Converts the trainerDTO and courseDTO into methods
        adds the trainers into the array of trainers inside the course
        and returns the course as a DTO
     */
    public CourseDTO setTrainer(TrainersDTO trainerDTO, CourseDTO courseDTO) {
        Course course = courseMapper.toEntity(courseDTO);
        Trainers trainer = trainersMapper.toEntity(trainerDTO);

        course.addTrainer(trainer);

        return courseMapper.toDTO(course);

    }
}
