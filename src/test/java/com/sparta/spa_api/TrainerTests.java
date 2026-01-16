package com.sparta.spa_api;

import com.sparta.spa_api.dtos.*;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.entities.Trainers;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.repository.TrainersRepository;
import com.sparta.spa_api.services.TrainerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TrainerTests {
    private final TrainersRepository mockTrainerRepository = Mockito.mock(TrainersRepository.class);
    private final StudentRepository mockStudentRepository = Mockito.mock(StudentRepository.class);
    private final CourseRepository mockCourseRepository = Mockito.mock(CourseRepository.class);

    private final TrainersMapper mockTrainersMapper =
            Mockito.mock(TrainersMapper.class);

    private final StudentMapper mockStudentMapper =
            Mockito.mock(StudentMapper.class);

    private final CourseMapper mockCourseMapper =
            Mockito.mock(CourseMapper.class);

    private final TrainerService sut = new TrainerService(mockTrainerRepository, mockTrainersMapper, mockCourseMapper, mockStudentRepository, mockStudentMapper);


    @Test
    @DisplayName("Ensure TrainerService is constructed correctly")
    void constructServiceTest() {
        Assertions.assertInstanceOf(TrainerService.class, sut);
    }

    @Test
    @DisplayName("Constructor should throw exception with null repository")
    void constructorWithNullRepositoryTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TrainerService(null, null, null, null, null));
    }

    // User Story 3.1.1
    @Test
    @DisplayName("Trainer can view their assigned course")
    void getTrainerCourseHappyPathTest() {

        Course course = new Course("Software Testing");
        Trainers trainer = new Trainers("Alice", course);

        TrainersDTO trainerDTO = new TrainersDTO();
        trainerDTO.setTrainer_name("Alice");
        trainerDTO.setCourse_id(course);

        Mockito.when(mockTrainerRepository.findById(1)).thenReturn(Optional.of(trainer));
        Mockito.when(mockTrainersMapper.toDTO(trainer)).thenReturn(trainerDTO);

        TrainersDTO result = sut.getTrainer(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Software Testing", result.getCourse_id().getCourseName());
    }
//
    // User Story 3.1.2
    @Test
    @DisplayName("Trainer cannot view course with invalid trainer ID")
    void getTrainerCourseInvalidIdTest() {

        Mockito.when(mockTrainerRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> sut.getTrainer(1));
    }
//
    // User Story 3.1.3
    @Test
    @DisplayName("Trainer cannot view course when no course is assigned")
    void getTrainerCourseNoAssignedCourseTest() {

        Trainers trainer = new Trainers();
        trainer.setTrainer_name("Alice");
        trainer.setCourse(null);

        TrainersDTO trainerDTO = new TrainersDTO();
        trainerDTO.setTrainer_name("Alice");
        trainerDTO.setCourse_id(null);

        Mockito.when(mockTrainerRepository.findById(1)).thenReturn(Optional.of(trainer));
        Mockito.when(mockTrainersMapper.toDTO(trainer)).thenReturn(trainerDTO);

        TrainersDTO result = sut.getTrainer(1);

        Assertions.assertNotNull(result);
        Assertions.assertNull(result.getCourse_id());
    }
//

//
    // User Story 3.3
    @Test
    @DisplayName("Update trainer unhappy path - trainer not found")
    void updateTrainerUnhappyPathTest() {

        Mockito.when(mockTrainerRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> sut.setTrainerName(1, "New Name"));
    }
}
