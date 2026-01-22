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

@Tag(name = "Course Details", description = "CRUD operations performed on course table")
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service){
        this.service = service;
    }

    @Operation(summary = "Get all courses")
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @Operation(summary = "Get a course by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.getCourseById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a course")
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO courseDTO) {
        try {
            courseDTO.setId(null);
            CourseDTO savedCourse = service.saveCourse(courseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Operation(summary = "Update a course")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int id, @Valid @RequestBody CourseDTO courseDTO) {
        try {
            courseDTO.setId(id);
            CourseDTO updated = service.updateCourse(id, courseDTO);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a course")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        try {
            service.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Search courses by name")
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourses(@RequestParam String name) {
        return ResponseEntity.ok(service.searchCoursesByName(name));
    }
}
