package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.*;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainersRepository trainersRepository;
    private final TrainersMapper trainersMapper;
    private final CourseMapper courseMapper;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Autowired
    public TrainerService(TrainersRepository trainersRepository, TrainersMapper trainersMapper, CourseMapper courseMapper, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.trainersRepository = trainersRepository;
        this.trainersMapper = trainersMapper;
        this.courseMapper = courseMapper;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;

        if(trainersRepository == null || trainersMapper == null || courseMapper == null || studentRepository == null) {
            throw new IllegalArgumentException("trainersRepository and courseRepository and studentRepository cannot be null!!!");
        }
    }

    public TrainersDTO getTrainer(int id) {
        Trainers trainer = trainersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Trainer with id: " + id));

        return trainersMapper.toDTO(trainer);
    }

    /*
        @3.1
        Retrieves the trainers Course and returns the course_id/course object
     */
    public CourseDTO getCourse(Integer Id) {
        Trainers trainer = trainersRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("No Trainer with id: " + Id));

        Course course = trainer.getCourse();
        if(!trainersRepository.existsById(course.getId())) {
            throw new NoSuchElementException("Course with id " + course.getId() + " does not exist");
        } else {
            return courseMapper.toDTO(course);
        }
    }

    /*
        @3.2
        Retrieves the course from the Trainer, and retrieves the students from the course.
        For each student in the course we convert toDTO and add into a separate array
        and finally return teh array.
     */
    public List<StudentDTO> getStudent(TrainersDTO trainerDTO) {
        Trainers entity = trainersMapper.toEntity(trainerDTO);
        Course course = entity.getCourse();

        ArrayList<StudentDTO> studentArray = new ArrayList<>();
        List<Student> myStudents = course.getStudents();

        for(Student student : myStudents) {
            StudentDTO studentDTO = studentMapper.toDTO(student);
            studentArray.add(studentDTO);
        }

        return studentArray;
    }

    /*
        @3.3.1
        Sets the Trainer's id and returns the Trainer.
     */
    public TrainersDTO setTrainerId(TrainersDTO trainer, Integer newId) {
        trainer.setId(newId);
        Trainers entity = trainersMapper.toEntity(trainer);
        trainersRepository.save(entity);
        return trainersMapper.toDTO(entity);
    }

    /*
        @3.3.1
        Sets the Trainer's name and returns the Trainer.
     */
    public TrainersDTO setTrainerName(int id, String newName) {
        Trainers trainer = trainersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Trainer with id: " + id));
        trainer.setTrainer_name(newName);
        return trainersMapper.toDTO(trainer);
    }

    /*
        @3.3.1
        Sets the Trainer's course and returns the Trainer.
     */
    public TrainersDTO setTrainerCourse(TrainersDTO trainer, Course newCourse) {
        trainer.setCourse_id(newCourse);
        Trainers entity = trainersMapper.toEntity(trainer);
        trainersRepository.save(entity);
        return trainersMapper.toDTO(entity);
    }

    /*
        @3.4

     */
    public CourseDTO setCourse_name(TrainersDTO trainerDTO, String newName) {
        Trainers entity = trainersMapper.toEntity(trainerDTO);
        Course course = entity.getCourse();
        course.setCourse_name(newName);

        if(!trainersRepository.existsById(course.getId())) {
            throw new NoSuchElementException("Course with id " + course.getId() + " does not exist");
        }

        return courseMapper.toDTO(course);
    }

    public void deleteTrainer(int id) {
        if (!trainersRepository.existsById(id)) {
            throw new NoSuchElementException("Trainer not found");
        }
        trainersRepository.deleteById(id);
    }




    /*
        3.1 As a Trainer, I want to view the courses I am assigned to so that I can prepare sessions.

        3.2  As a Trainer,  I want to view the trainees enrolled on my courses so that I can manage attendance.

        3.3  As a Trainer, I want to update my personal details

        3.4 I want to update course information so that course content remains up to date.
     */


}
