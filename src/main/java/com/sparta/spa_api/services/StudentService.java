package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.dtos.StudentMapper;
import com.sparta.spa_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        if (studentRepository == null || studentMapper == null) {
            throw new IllegalArgumentException("Repository and Mapper cannot be null");
        }

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDTO getStudentByID(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        return studentMapper.toDTO(student);
    }


    public StudentDTO getStudentName(String student_name) {
        Student student = studentRepository.findByStudentName(student_name);
        return studentMapper.toDTO(student);
    }

    public StudentDTO getStudentCourse(Integer courseId) {
        Student student = studentRepository.findByCourseId(courseId);
        return studentMapper.toDTO(student);
    }

    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean hasGraduated(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        boolean status = student.isHasGraduated();
        return studentRepository.hasGraduated(status);

    }


    public StudentDTO updateStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return studentMapper.toDTO(studentRepository.save(student));
        } else {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " does not exist.");
        }
    }





}
