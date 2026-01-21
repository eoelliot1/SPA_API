package com.sparta.spa_api.services;



import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.dtos.StudentMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;




    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, CourseRepository courseRepository) {
        if (studentRepository == null || studentMapper == null) {
            throw new IllegalArgumentException("Repository and Mapper cannot be null");
        }

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.courseRepository = courseRepository;


    }

    public List<StudentDTO> getAllStudents(){

        return studentRepository.findAll().stream().map(studentMapper::toDTO).toList();
    }


    public StudentDTO getStudentByID(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return studentMapper.toDTO(student);
    }







    public boolean deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }



    public StudentDTO updateStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return studentMapper.toDTO(studentRepository.save(student));
        } else {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " does not exist.");
        }

    }
    public StudentDTO saveStudent(StudentDTO studentDTO) {

        Student student = studentMapper.toEntity(studentDTO);

        if (studentDTO.getCourseId() != null) {
            Course course = courseRepository.findById(studentDTO.getCourseId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Course not found with ID " + studentDTO.getCourseId()
                            )
                    );
            student.setCourse(course);
        }

        Student saved = studentRepository.save(student);
        return studentMapper.toDTO(saved);
    }

    public boolean hasGraduated(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student.isHasGraduated();
    }









}
