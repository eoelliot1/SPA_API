package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.dtos.StudentMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, CourseRepository courseRepository) {
        if (studentRepository == null || studentMapper == null || courseRepository == null) {
            throw new IllegalArgumentException("Repositories and Mapper cannot be null");
        }
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.courseRepository = courseRepository;
    }

    // Get all students
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get student by ID
    public StudentDTO getStudentById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID " + id));
        return studentMapper.toDTO(student);
    }


    // Save a new student
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentName(studentDTO.getStudentName());
        student.setHasGraduated(studentDTO.isGraduated());

        // set course if courseId is provided
        if (studentDTO.getCourseId() != null) {
            Course course = courseRepository.findById(studentDTO.getCourseId())
                    .orElseThrow(() -> new IllegalArgumentException("Course not found with ID " + studentDTO.getCourseId()));
            student.setCourse(course);
        }

        Student saved = studentRepository.save(student);
        return studentMapper.toDTO(saved);
    }

    // Update existing student
    public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID " + id));

        student.setStudentName(studentDTO.getStudentName());
        student.setHasGraduated(studentDTO.isGraduated());

        // update course if provided
        if (studentDTO.getCourseId() != null) {
            Course course = courseRepository.findById(studentDTO.getCourseId())
                    .orElseThrow(() -> new IllegalArgumentException("Course not found with ID " + studentDTO.getCourseId()));
            student.setCourse(course);
        }

        Student updated = studentRepository.save(student);
        return studentMapper.toDTO(updated);
    }


    // Delete student
    public void deleteStudent(Integer id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException("Student not found with ID " + id);
        }
        studentRepository.deleteById(id);
    }

    // Check graduation status
    public boolean hasGraduated(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID " + id));
        return student.isHasGraduated();
    }

    // Get all students (Entity)
    public List<Student> getAllStudentEntities() {
        return studentRepository.findAll();
    }

    // Get student by ID (Entity)
    public Student getStudentEntityById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID " + id));
    }
}
