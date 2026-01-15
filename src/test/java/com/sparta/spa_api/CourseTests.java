package com.sparta.spa_api;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.repository.CourseRepository;
import com.sparta.spa_api.services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CourseTests {
    private final CourseRepository mockRepository = Mockito.mock(CourseRepository.class);
    private final CourseMapper mockMapper = Mockito.mock(CourseMapper.class);
    private final CourseService sut = new CourseService(mockRepository, mockMapper);


    @Test
    @DisplayName("Ensure CourseService is constructed correctly")
    void constructServiceTest() {
        Assertions.assertInstanceOf(CourseService.class, sut);
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
    void updateCourseHappyPathTest() {

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

        boolean result = sut.deleteCourse(1);

        Assertions.assertTrue(result);
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

    // User Story 3.4
    @Test
    @DisplayName("Course information can be updated")
    void updateCourseHappyPathTest() {

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
