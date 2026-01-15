package com.sparta.spa_api;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.entities.Student;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CourseTests {
    private final CourseRepository mockRepository = Mockito.mock(CourseRepository.class);
    private final CourseMapper mockMapper = Mockito.mock(CourseMapper.class);
    private final CourseService sut = new CourseService(mockRepository, mockMapper);


    @Test
    @DisplayName("Service constructs correctly")
    public void constructServiceTest() {
        Assertions.assertInstanceOf(CourseService.class, sut);
    }

    @Test
    @DisplayName("Constructor throws exception if null arguments")
    public void constructorNullArgsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CourseService(null, null));
    }

    @Test
    @DisplayName("Get all courses returns DTOs")
    public void getAllCoursesDTOTest() {
        // Arrange
        Course c1 = new Course("Software Testing");
        Course c2 = new Course("Data");

        CourseDTO dto1 = new CourseDTO();
        dto1.setCourse_name("Software Testing");
        CourseDTO dto2 = new CourseDTO();
        dto2.setCourse_name("Data");

        Mockito.when(mockRepository.findAll()).thenReturn(List.of(c1, c2));
        Mockito.when(mockMapper.toDTO(c1)).thenReturn(dto1);
        Mockito.when(mockMapper.toDTO(c2)).thenReturn(dto2);

        // Act
        List<CourseDTO> result = sut.getAllCourses();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Software Testing", result.get(0).getCourse_name());
        Assertions.assertEquals("Data", result.get(1).getCourse_name());
    }


    @Test
    @DisplayName("Get course by ID returns DTO")
    public void getCourseByIdDTOTest() {
        // Arrange
        Course course = new Course("Software Testing");
        CourseDTO dto = new CourseDTO();
        dto.setCourse_name("Software Testing");

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(course));
        Mockito.when(mockMapper.toDTO(course)).thenReturn(dto);

        // Act
        CourseDTO result = sut.getCourseById(1);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Software Testing", result.getCourse_name());
    }

    @Test
    @DisplayName("Get course by invalid ID throws exception")
    public void getCourseByInvalidIdTest() {
        Mockito.when(mockRepository.findById(999)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> sut.getCourseById(999));
    }


    @Test
    @DisplayName("Save course returns DTO")
    public void saveCourseDTOTest() {
        Course course = new Course("DevOps");
        CourseDTO dto = new CourseDTO();
        dto.setCourse_name("DevOps");

        Mockito.when(mockRepository.save(course)).thenReturn(course);
        Mockito.when(mockMapper.toDTO(course)).thenReturn(dto);

        CourseDTO result = sut.saveCourse(CourseDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("DevOps", result.getCourse_name());
    }


    @Test
    @DisplayName("Delete course success returns true")
    public void deleteCourseSuccessTest() {
        Mockito.when(mockRepository.existsById(1)).thenReturn(true);

        boolean result = sut.deleteCourse(1);

        Assertions.assertTrue(result);
        Mockito.verify(mockRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Delete non-existent course returns false")
    public void deleteCourseFailTest() {
        Mockito.when(mockRepository.existsById(999)).thenReturn(false);

        boolean result = sut.deleteCourse(999);

        Assertions.assertFalse(result);
        Mockito.verify(mockRepository, Mockito.never()).deleteById(Mockito.anyInt());

    }

    //Costom method/filter test
    @Test
    @DisplayName("Search courses by keyword returns DTOs")
    public void searchCoursesByNameTest() {
        Course course1 = new Course("Software Testing");
        Course course2 = new Course("Data Analysis");

        CourseDTO dto2 = new CourseDTO();
        dto2.setCourse_name("Data Analysis");

        Mockito.when(mockRepository.findByCourse_nameContainingIgnoreCase("Data"))
                .thenReturn(List.of(course2));
        Mockito.when(mockMapper.toDTO(course2)).thenReturn(dto2);

        List<CourseDTO> result = sut.searchCoursesByName("Data");

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Data Analysis", result.get(0).getCourse_name());
    }
    //User story 1.1.1
    @Test
    @DisplayName("Create course successfully returns DTO")
    public void createCourseSuccessTest() {
        Course newCourse = new Course("Big Data Analytics");
        CourseDTO dto = new CourseDTO();
        dto.setCourse_name("AI Basics");

        // No duplicates
        Mockito.when(mockRepository.findAll()).thenReturn(List.of());
        Mockito.when(mockRepository.save(newCourse)).thenReturn(newCourse);
        Mockito.when(mockMapper.toDTO(newCourse)).thenReturn(dto);

        CourseDTO result = sut.createCourse(newCourse);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Big Data Analytics", result.getCourse_name());
    }

    //user story 1.1.2
    @Test
    @DisplayName("Create course fails if required fields missing")
    public void createCourseMissingFieldsTest() {
        Course newCourse = new Course(); // name is null

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sut.createCourse(newCourse)
        );

        Assertions.assertEquals("Course name is required", exception.getMessage());
    }

    //User story 1.1.3
    @Test
    @DisplayName("Create course fails if duplicate name exists")
    public void createCourseDuplicateNameTest() {
        Course existingCourse = new Course("Software Testing");
        Course newCourse = new Course("Software Testing");

        Mockito.when(mockRepository.findAll()).thenReturn(List.of(existingCourse));

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sut.createCourse(newCourse)
        );

        Assertions.assertEquals("Course with this name already exists", exception.getMessage());
    }


    // ------------------------
    // 1.2.1 View all courses
    // ------------------------
    @Test
    @DisplayName("Admin can view all courses")
    void viewAllCoursesHappyPathTest() {

        // Arrange
        Course course1 = new Course("Java");
        Course course2 = new Course("Python");
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);

        CourseDTO dto1 = new CourseDTO();
        dto1.setCourse_name("Java");
        CourseDTO dto2 = new CourseDTO();
        dto2.setCourse_name("Python");

        Mockito.when(mockRepository.findAll()).thenReturn(courses);
        Mockito.when(mockMapper.toDTO(course1)).thenReturn(dto1);
        Mockito.when(mockMapper.toDTO(course2)).thenReturn(dto2);

        // Act
        List<CourseDTO> result = sut.getAllCourses();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Java", result.get(0).getCourse_name());
        Assertions.assertEquals("Python", result.get(1).getCourse_name());
    }

    // ------------------------
    // 1.2.2 View course by ID
    // ------------------------
    @Test
    @DisplayName("Admin can view course by ID")
    void viewCourseByIdHappyPathTest() {

        Course course = new Course("Java");
        CourseDTO dto = new CourseDTO();
        dto.setCourse_name("Java");

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(course));
        Mockito.when(mockMapper.toDTO(course)).thenReturn(dto);

        CourseDTO result = sut.getCourseById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Java", result.getCourse_name());
    }

    // ------------------------
    // 1.2.3 Course not found
    // ------------------------
    @Test
    @DisplayName("View course by ID - course not found")
    void viewCourseByIdNotFoundTest() {

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> sut.getCourseById(1));
    }

    // ------------------------
    // 1.2.4 Update course
    // ------------------------
    @Test
    @DisplayName("Admin can update a course")
    void adminUpdateCourseHappyPathTest() {

        Course course = new Course("Old Name");
        CourseDTO dto = new CourseDTO();
        dto.setCourse_name("New Name");

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(course));
        Mockito.when(mockRepository.save(course)).thenReturn(course);
        Mockito.when(mockMapper.toDTO(course)).thenReturn(dto);

        CourseDTO updated = sut.updateCourse(1, dto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("New Name", updated.getCourse_name());
    }

    // ------------------------
    // 1.2.5 Delete course
    // ------------------------

    @Test
    @DisplayName("Admin can delete a course")
    void deleteCourseHappyPathTest() {

        Mockito.when(mockRepository.existsById(1)).thenReturn(true);

        sut.deleteCourse(1);

        Mockito.verify(mockRepository).deleteById(1);
    }

    // ------------------------
    // 1.2.6 Delete course with invalid ID
    // ------------------------
    @Test
    @DisplayName("Admin cannot delete course with invalid ID")
    void deleteCourseInvalidIdTest() {

        Mockito.when(mockRepository.existsById(1)).thenReturn(false);

        Assertions.assertThrows(NoSuchElementException.class, () -> sut.deleteCourse(1));
    }

    // ------------------------
    // 1.3.1 View courses and trainees
    // ------------------------
    @Test
    @DisplayName("Admin or Trainer can view trainees enrolled on a course")
    void viewCourseTraineesHappyPathTest() {

        Course course = new Course("Java");

        Student student1 = new Student("Alice", false, course);
        Student student2 = new Student("Bob", false, course);

        course.getStudents().add(student1);
        course.getStudents().add(student2);

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.of(course));

        List<Student> result = sut.getTraineesByCourseId(1);

        Assertions.assertEquals(2, result.size());
    }

    // ------------------------
    // 1.3.2 No enrolled trainees
    // ------------------------
    @Test
    @DisplayName("Course has no enrolled trainees")
    void viewCourseWithNoTraineesTest() {

        Course course = new Course("Python");

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.of(course));

        List<Student> result = sut.getTraineesByCourseId(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }

    // ------------------------
    // 1.3.3 Invalid course ID
    // ------------------------
    @Test
    @DisplayName("Cannot view trainees for invalid course ID")
    void viewCourseTraineesInvalidCourseIdTest() {

        Mockito.when(mockRepository.findById(1))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> sut.getTraineesByCourseId(1)
        );
    }

    // User Story 3.4
    @Test
    @DisplayName("Course information can be updated")
    void updateCourseInformationHappyPathTest() {

        Course course = new Course("Old Course Name");
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourse_name("New Course Name");

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.of(course));
        Mockito.when(mockRepository.save(course)).thenReturn(course);
        Mockito.when(mockMapper.toDTO(course)).thenReturn(courseDTO);

        CourseDTO updatedCourse = sut.updateCourse(1, courseDTO);

        Assertions.assertNotNull(updatedCourse);
        Assertions.assertEquals("New Course Name", updatedCourse.getCourse_name());
    }

    @Test
    @DisplayName("Update course unhappy path - course not found")
    void updateCourseUnhappyPathTest() {

        Mockito.when(mockRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> sut.updateCourse(1, new CourseDTO()));
    }
}
