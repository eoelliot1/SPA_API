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

@Tag(name = "Course Details", description = "Operations on course table")
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
        return ResponseEntity.ok(service.getCourseById(id));
    }

    @Operation(summary = "Create a course")
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO dto) {
        dto.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCourse(dto));
    }

    @Operation(summary = "Update a course")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable int id, @Valid @RequestBody CourseDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.updateCourse(id, dto));
    }

    @Operation(summary = "Delete a course")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

//    @Operation(summary = "Search courses by name")
//    @GetMapping("/search")
//    public ResponseEntity<List<CourseDTO>> searchCourses(@RequestParam String name) {
//        return ResponseEntity.ok(service.searchCoursesByName(name));
//    }
}
