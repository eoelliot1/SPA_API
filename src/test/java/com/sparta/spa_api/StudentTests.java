//
//
//package com.sparta.spa_api;
//
//import com.sparta.spa_api.dtos.StudentDTO;
//import com.sparta.spa_api.dtos.StudentMapper;
//import com.sparta.spa_api.entities.Course;
//import com.sparta.spa_api.entities.Student;
//import com.sparta.spa_api.entities.Trainers;
//import com.sparta.spa_api.repository.CourseRepository;
//import com.sparta.spa_api.repository.StudentRepository;
//import com.sparta.spa_api.services.StudentService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//public class StudentServiceTests {
//
//    private final StudentRepository mockStudentRepo = Mockito.mock(StudentRepository.class);
//    private final CourseRepository mockCourseRepo = Mockito.mock(CourseRepository.class);
//    private final StudentMapper mockMapper = Mockito.mock(StudentMapper.class);
//
//    private final StudentService sut = new StudentService(mockStudentRepo, mockCourseRepo, mockMapper);
//
//    // ===== Constructor Tests =====
//    @Test
//    @DisplayName("StudentService is constructed correctly")
//    void constructServiceTest() {
//        Assertions.assertInstanceOf(StudentService.class, sut);
//    }
//
//    @Test
//    @DisplayName("Constructor throws exception if dependencies are null")
//    void constructorWithNullDependenciesTest() {
//        Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new StudentService(null, null, null)
//        );
//    }
//
//    // Get All Students
//    @Test
//    @DisplayName("Get all students returns list of StudentDTOs")
//    void getAllStudentsTest() {
//        Course course = new Course("Data");
//        Student student1 = new Student("Alice", false, course);
//        Student student2 = new Student("Bob", true, course);
//
//        StudentDTO dto1 = new StudentDTO();
//        dto1.setStudentName("Alice");
//
//        StudentDTO dto2 = new StudentDTO();
//        dto2.setStudentName("Bob");
//
//        Mockito.when(mockStudentRepo.findAll()).thenReturn(List.of(student1, student2));
//        Mockito.when(mockMapper.toDTO(student1)).thenReturn(dto1);
//        Mockito.when(mockMapper.toDTO(student2)).thenReturn(dto2);
//
//        List<StudentDTO> result = sut.getAllStudents();
//
//        Assertions.assertEquals(2, result.size());
//        Assertions.assertEquals("Alice", result.get(0).getStudentName());
//        Assertions.assertEquals("Bob", result.get(1).getStudentName());
//    }
//
//    //  Get Student by ID
//    @Test
//    @DisplayName("Get student by ID - happy path")
//    void getStudentByIdTest() {
//        Course course = new Course("Software Testing");
//        Student student = new Student("Alice", false, course);
//
//        StudentDTO dto = new StudentDTO();
//        dto.setStudentName("Alice");
//
//        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));
//        Mockito.when(mockMapper.toDTO(student)).thenReturn(dto);
//
//        StudentDTO result = sut.getStudentById(1);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("Alice", result.getStudentName());
//    }
//
//    @Test
//    @DisplayName("Get student by ID throws exception when student not found")
//    void getStudentByIdNotFoundTest() {
//        Mockito.when(mockStudentRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(
//                NoSuchElementException.class,
//                () -> sut.getStudentById(999)
//        );
//    }
//
//    @Test
//    @DisplayName("findById is called once when retrieving student")
//    void verifyFindByIdCalledOnce() {
//        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.empty());
//
//        try {
//            sut.getStudentById(1);
//        } catch (Exception ignored) {}
//
//        Mockito.verify(mockStudentRepo, Mockito.times(1)).findById(1);
//    }
//
//    //  User Story 2.1.1
//    @Test
//    @DisplayName("Enrol student on a course - happy path")
//    void enrolStudentHappyPath() {
//        Course course = new Course("Data");
//        Student student = new Student("Alice", false, null);
//
//        Mockito.when(mockCourseRepo.findById(1)).thenReturn(Optional.of(course));
//        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));
//        Mockito.when(mockStudentRepo.save(student)).thenReturn(student);
//
//        StudentDTO dto = new StudentDTO();
//        dto.setStudentName("Alice");
//
//        Mockito.when(mockMapper.toDTO(student)).thenReturn(dto);
//
//        StudentDTO result = sut.enrolStudent(1, 1);
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("Alice", result.getStudentName());
//        Mockito.verify(mockStudentRepo).save(student);
//    }
//
//    //  User Story 2.2
//    @Test
//    @DisplayName("Get enrolled courses for student")
//    void getEnrolledCoursesTest() {
//        Course course = new Course("Software Testing");
//        Student student = new Student("Alice", false, course);
//
//        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));
//
//        List<Course> result = sut.getEnrolledCourses(1);
//
//        Assertions.assertEquals(1, result.size());
//        Assertions.assertEquals("Software Testing", result.get(0).getCourseName());
//    }
//
//    // User Story 2.3
//    @Test
//    @DisplayName("Get trainers for trainee's course")
//    void getTrainersForStudentCourseTest() {
//        Course course = new Course("Data");
//        Trainers trainer1 = new Trainers("John Trainer", course);
//        course.setTrainers(List.of(trainer1));
//
//        Student student = new Student("Alice", false, course);
//
//        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));
//
//        List<Trainers> trainers = sut.getTrainersForStudentCourse(1);
//
//        Assertions.assertNotNull(trainers);
//        Assertions.assertEquals(1, trainers.size());
//        Assertions.assertEquals("John Trainer", trainers.get(0).getTrainerName());
//    }
//
//    // User Story 2.4
//    @Test
//    @DisplayName("Update trainee personal details")
//    void updateStudentDetailsTest() {
//        Student student = new Student("Alice", false, null);
//        StudentDTO dto = new StudentDTO();
//        dto.setStudentName("Alice Updated");
//
//        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));
//        Mockito.when(mockStudentRepo.save(student)).thenReturn(student);
//        Mockito.when(mockMapper.toDTO(student)).thenReturn(dto);
//
//        StudentDTO result = sut.updateStudent(1, dto);
//
//        Assertions.assertEquals("Alice Updated", result.getStudentName());
//    }
//
//    //  User Story 2.1.2
//
//    @Test
//    @DisplayName("Enrol student already enrolled throws exception")
//    void enrolStudentAlreadyEnrolled() {
//        Course course = new Course("Data");
//        Student student = new Student("Alice", false, course);
//
//        Mockito.when(mockCourseRepo.findById(1)).thenReturn(Optional.of(course));
//        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));
//
//        Assertions.assertThrows(
//                IllegalStateException.class,
//                () -> sut.enrolStudent(1, 1)
//        );
//    }
//    //  User Story 2.1.3
//
//    @Test
//    @DisplayName("Enrol student fails when course does not exist")
//    void enrolStudentCourseNotFound() {
//        Mockito.when(mockCourseRepo.findById(99)).thenReturn(Optional.empty());
//
//        Assertions.assertThrows(
//                NoSuchElementException.class,
//                () -> sut.enrolStudent(99, 1)
//        );
//    }
//
//}
//
//