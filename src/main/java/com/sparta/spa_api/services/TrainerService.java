package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.dtos.StudentMapper;
import com.sparta.spa_api.dtos.TrainersDTO;
import com.sparta.spa_api.dtos.TrainersMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrainerService {

    private final TrainersRepository trainersRepository;
    private final TrainersMapper trainersMapper;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;

    public TrainerService(TrainersRepository trainersRepository,
                          TrainersMapper trainersMapper,
                          CourseRepository courseRepository,
                          CourseMapper courseMapper,
                          StudentMapper studentMapper) {

        if (trainersRepository == null || trainersMapper == null
                || courseRepository == null || courseMapper == null
                || studentMapper == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.trainersRepository = trainersRepository;
        this.trainersMapper = trainersMapper;
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.studentMapper = studentMapper;
    }

    public TrainersDTO getTrainerById(int id) {
        Trainers trainer = trainersRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Trainer not found"
                        )
                );
        return trainersMapper.toDTO(trainer);
    }

    public CourseDTO getTrainerCourse(int trainerId) {
        Trainers trainer = trainersRepository.findById(trainerId)
                .orElseThrow(() -> new NoSuchElementException("Trainer not found"));
        return courseMapper.toDTO(trainer.getCourse());
    }

    public List<StudentDTO> getStudentsByTrainerId(int trainerId) {
        Trainers trainer = trainersRepository.findById(trainerId)
                .orElseThrow(() -> new NoSuchElementException("Trainer not found"));

        return trainer.getCourse().getStudents()
                .stream()
                .map(studentMapper::toDTO)
                .toList();
    }

    public TrainersDTO saveTrainer(TrainersDTO dto) {
        Trainers trainer = trainersMapper.toEntity(dto);

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        trainer.setCourse(course);

        return trainersMapper.toDTO(trainersRepository.save(trainer));
    }

    public TrainersDTO updateTrainer(int id, TrainersDTO dto) {
        Trainers trainer = trainersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Trainer not found"));

        trainer.setTrainerName(dto.getTrainerName());

        if (dto.getCourseId() != null) {
            Course course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new IllegalArgumentException("Course not found"));
            trainer.setCourse(course);
        }

        return trainersMapper.toDTO(trainersRepository.save(trainer));
    }

    public void deleteTrainer(int id) {
        if (!trainersRepository.existsById(id)) {
            throw new NoSuchElementException("Trainer not found");
        }
        trainersRepository.deleteById(id);
    }

    public List<TrainersDTO> getAllTrainers() {
        return trainersRepository.findAll().stream()
                .map(trainersMapper::toDTO)
                .toList();
    }
}
