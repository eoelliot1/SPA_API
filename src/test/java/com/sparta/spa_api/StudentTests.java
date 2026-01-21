package com.sparta.spa_api;

import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.dtos.StudentMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.repository.StudentRepository;
import com.sparta.spa_api.services.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class StudentTests {

    private final StudentRepository mockStudentRepo = Mockito.mock(StudentRepository.class);
    private final StudentMapper mockMapper = Mockito.mock(StudentMapper.class);
    private final CourseMapper mockCourseMapper = Mockito.mock(CourseMapper.class);
    private final CourseRepository courseRepository = Mockito.mock(CourseRepository.class);

    private final StudentService sut =
            new StudentService(mockStudentRepo, mockMapper, courseRepository);

    // ===== Constructor Tests =====

    @Test
    @DisplayName("StudentService is constructed correctly")
    void constructServiceTest() {
        Assertions.assertInstanceOf(StudentService.class, sut);
    }

    @Test
    @DisplayName("Constructor throws exception if dependencies are null")
    void constructorWithNullDependenciesTest() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new StudentService(null, null, null)
        );
    }

    // ===== Get All Students =====

    @Test
    @DisplayName("Get all students returns list of StudentDTOs")
    void getAllStudentsTest() {
        Course course = new Course("Data");
        Student student1 = new Student("Alice", false, course);
        Student student2 = new Student("Bob", true, course);

        StudentDTO dto1 = new StudentDTO();
        dto1.setStudentName("Alice");

        StudentDTO dto2 = new StudentDTO();
        dto2.setStudentName("Bob");

        Mockito.when(mockStudentRepo.findAll()).thenReturn(List.of(student1, student2));
        Mockito.when(mockMapper.toDTO(student1)).thenReturn(dto1);
        Mockito.when(mockMapper.toDTO(student2)).thenReturn(dto2);

        List<StudentDTO> result = sut.getAllStudents();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Alice", result.get(0).getStudentName());
        Assertions.assertEquals("Bob", result.get(1).getStudentName());
    }

    // ===== Get Student By ID =====

    @Test
    @DisplayName("Get student by ID - student exists")
    void getStudentByIdTest() {
        Course course = new Course("Software Testing");
        Student student = new Student("Alice", false, course);

        StudentDTO dto = new StudentDTO();
        dto.setStudentName("Alice");

        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));
        Mockito.when(mockMapper.toDTO(student)).thenReturn(dto);

        StudentDTO result = sut.getStudentByID(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Alice", result.getStudentName());
    }

    @Test
    @DisplayName("Get student by ID returns null when student not found")
    void getStudentByIdNotFoundTest() {
        Mockito.when(mockStudentRepo.findById(99)).thenReturn(Optional.empty());
        Mockito.when(mockMapper.toDTO(null)).thenReturn(null);

        StudentDTO result = sut.getStudentByID(99);

        Assertions.assertNull(result);
    }

    // ===== Update Student =====
    @Test
    @DisplayName("Update student when student exists")
    void updateStudentTest() {

        Student student = Mockito.spy(new Student("Alice", false, null));

        Mockito.when(student.getId()).thenReturn(1);

        StudentDTO dto = new StudentDTO();
        dto.setStudentName("Alice Updated");

        Mockito.when(mockStudentRepo.existsById(1)).thenReturn(true);
        Mockito.when(mockStudentRepo.save(student)).thenReturn(student);
        Mockito.when(mockMapper.toDTO(student)).thenReturn(dto);

        StudentDTO result = sut.updateStudent(student);

        Assertions.assertEquals("Alice Updated", result.getStudentName());
    }


    @Test
    @DisplayName("Update student throws exception when student does not exist")
    void updateStudentNotFoundTest() {

        Student student = Mockito.spy(new Student("Alice", false, null));

        Mockito.when(student.getId()).thenReturn(99);
        Mockito.when(mockStudentRepo.existsById(99)).thenReturn(false);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sut.updateStudent(student)
        );
    }
    // ===== Delete Student =====

    @Test
    @DisplayName("Delete student returns true when student exists")
    void deleteStudentSuccessTest() {
        Mockito.when(mockStudentRepo.existsById(1)).thenReturn(true);

        boolean result = sut.deleteStudent(1);

        Assertions.assertTrue(result);
        Mockito.verify(mockStudentRepo).deleteById(1);
    }

    @Test
    @DisplayName("Delete student returns false when student does not exist")
    void deleteStudentFailTest() {
        Mockito.when(mockStudentRepo.existsById(99)).thenReturn(false);

        boolean result = sut.deleteStudent(99);

        Assertions.assertFalse(result);
    }

    // ===== Has Graduated =====

    @Test
    @DisplayName("hasGraduated returns correct value")
    void hasGraduatedTest() {
        Student student = new Student("Alice", true, null);

        Mockito.when(mockStudentRepo.findById(1)).thenReturn(Optional.of(student));

        boolean result = sut.hasGraduated(1);

        Assertions.assertTrue(result);
    }
}
