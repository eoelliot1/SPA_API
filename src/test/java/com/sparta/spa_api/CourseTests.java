package com.sparta.spa_api.services;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.CourseMapper;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

        CourseDTO result = sut.saveCourse(course);

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

}
