package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service){

        this.service = service;
    }


    @Operation(summary = "Get all courses", description = "Retrieve a list of all courses")
    @GetMapping(value = "/")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = service.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @Operation(summary = "Get a course by ID", description = "Retrieve a course from the database using their unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String id) {
        CourseDTO course = service.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new course", description = "Create a new course in the database")
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        CourseDTO savedCourse = service.saveCourse(courseDTO);
        return ResponseEntity.status(201).body(savedCourse);
    }

    @Operation(summary = "Update a course", description = "Update an existing course in the database")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String id, @RequestBody CourseDTO courseDTO) {
        courseDTO.setCourse_id(Integer.valueOf(id));
        try {
            CourseDTO updatedCourse = service.updateCourse(Integer.valueOf(id), courseDTO);
            return ResponseEntity.ok(updatedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a course", description = "Delete a course from the database using their unique ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        boolean deleted = service.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
