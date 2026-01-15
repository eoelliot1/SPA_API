//package com.sparta.spa_api;
//
//import com.sparta.spa_api.dtos.TrainersDTO;
//import com.sparta.spa_api.dtos.TrainersMapper;
//import com.sparta.spa_api.entities.Course;
//import com.sparta.spa_api.entities.Student;
//import com.sparta.spa_api.entities.Trainers;
//import com.sparta.spa_api.repository.TrainersRepository;
//import com.sparta.spa_api.services.TrainerService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//public class TrainerTests {
//    private final TrainersRepository mockRepository = Mockito.mock(TrainersRepository.class);
//
//    private final TrainersMapper mockMapper =
//            Mockito.mock(TrainersMapper.class);
//
//    private final TrainerService sut = new TrainerService(mockRepository, mockMapper);
//
//
//    @Test
//    @DisplayName("Ensure TrainerService is constructed correctly")
//    void constructServiceTest() {
//        Assertions.assertInstanceOf(TrainerService.class, sut);
//    }
//
//    @Test
//    @DisplayName("Constructor should throw exception with null repository")
//    void constructorWithNullRepositoryTest() {
//        Assertions.assertThrows(IllegalArgumentException.class, () -> new TrainerService(null, null));
//    }
//
//    // User Story 3.1.1
//    @Test
//    @DisplayName("Trainer can view their assigned course")
//    void getTrainerCourseHappyPathTest() {
//
//        Course course = new Course("Software Testing");
//        Trainers trainer = new Trainers("Alice", course);
//
//        TrainersDTO trainerDTO = new TrainersDTO();
//        trainerDTO.setTrainer_name("Alice");
//        trainerDTO.setCourse_id(course);
//
//        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(trainer));
//        Mockito.when(mockMapper.toDTO(trainer)).thenReturn(trainerDTO);
//
//        TrainersDTO result = sut.getTrainerById(1);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("Software Testing", result.getCourse_id().getCourse_name());
//    }
//
//    // User Story 3.1.2
//    @Test
//    @DisplayName("Trainer cannot view course with invalid trainer ID")
//    void getTrainerCourseInvalidIdTest() {
//
//        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(NoSuchElementException.class, () -> sut.getTrainerById(1));
//    }
//
//    // User Story 3.1.3
//    @Test
//    @DisplayName("Trainer cannot view course when no course is assigned")
//    void getTrainerCourseNoAssignedCourseTest() {
//
//        Trainers trainer = new Trainers();
//        trainer.setTrainer_name("Alice");
//        trainer.setCourse(null);
//
//        TrainersDTO trainerDTO = new TrainersDTO();
//        trainerDTO.setTrainer_name("Alice");
//        trainerDTO.setCourse_id(null);
//
//        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(trainer));
//        Mockito.when(mockMapper.toDTO(trainer)).thenReturn(trainerDTO);
//
//        TrainersDTO result = sut.getTrainerById(1);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertNull(result.getCourse_id());
//    }
//
//
//    // User Story 3.2
//    @Test
//    @DisplayName("Trainer can view students enrolled on their course")
//    void getStudentsForTrainerCourseTest() {
//
//        Course course = new Course("Java");
//        Student student1 = new Student("John", false, course);
//        Student student2 = new Student("Sarah", false, course);
//
//        course.getStudents().add(student1);
//        course.getStudents().add(student2);
//
//        Trainers trainer = new Trainers("Bob", course);
//
//        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(trainer));
//
//        List<Student> students = sut.getStudentsByTrainerId(1);
//
//        Assertions.assertEquals(2, students.size());
//    }
//
//    // User Story 3.3
//    @Test
//    @DisplayName("Trainer can update their personal details")
//    void updateTrainerHappyPathTest() {
//
//        Course course = new Course("DevOps");
//        Trainers trainer = new Trainers("Old Name", course);
//
//        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(trainer));
//        Mockito.when(mockRepository.save(trainer)).thenReturn(trainer);
//
//        Trainers updatedTrainer = sut.updateTrainerName(1, "New Name");
//
//        Assertions.assertEquals("New Name", updatedTrainer.getTrainer_name());
//    }
//
//    @Test
//    @DisplayName("Update trainer unhappy path - trainer not found")
//    void updateTrainerUnhappyPathTest() {
//
//        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(NoSuchElementException.class, () -> sut.updateTrainerName(1, "New Name"));
//    }
//}
