package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "course-controller", description = "Operations on course table")
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service){

        this.service = service;
    }


    @Operation(summary = "Get all courses", description = "Retrieve a list of all courses")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = service.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @Operation(summary = "Get a course by ID", description = "Retrieve a course from the database using their unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {
        CourseDTO course = service.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Adds a course", description = "Creates a new course in the db")
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO)
    {
        courseDTO.setId(null);
        CourseDTO savedCourse = service.saveCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }
    @Operation(summary = "Update a course", description = "Update an existing course in the database")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int id, @RequestBody CourseDTO courseDTO) {
        courseDTO.setId(id);
        try {
            CourseDTO updatedCourse = service.updateCourse(id, courseDTO);
            return ResponseEntity.ok(updatedCourse);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a course", description = "Delete a course from the database using their unique ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search courses by name")
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourses(
            @RequestParam String name) {
        return ResponseEntity.ok(service.searchCoursesByName(name));
    }


}
