package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.TrainersDTO;
import com.sparta.spa_api.dtos.StudentDTO;
import com.sparta.spa_api.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainersController {

    private final TrainerService service;

    public TrainersController(TrainerService service) {
        this.service = service;
    }

    @Operation(summary = "Get Trainer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TrainersDTO> getTrainer(@PathVariable int id) {
        return ResponseEntity.ok(service.getTrainerById(id));
    }

    @Operation(summary = "Get Trainer's Course")
    @GetMapping("/{id}/course")
    public ResponseEntity<CourseDTO> getTrainerCourse(@PathVariable int id) {
        return ResponseEntity.ok(service.getTrainerCourse(id));
    }

    @Operation(summary = "Get Students for Trainer")
    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getStudents(@PathVariable int id) {
        return ResponseEntity.ok(service.getStudentsByTrainerId(id));
    }

    @Operation(summary = "Create Trainer")
    @PostMapping
    public ResponseEntity<TrainersDTO> createTrainer(@RequestBody TrainersDTO dto) {
        return ResponseEntity.status(201).body(service.saveTrainer(dto));
    }

    @Operation(summary = "Update Trainer")
    @PutMapping("/{id}")
    public ResponseEntity<TrainersDTO> updateTrainer(@PathVariable int id, @RequestBody TrainersDTO dto) {
        return ResponseEntity.ok(service.updateTrainer(id, dto));
    }

    @Operation(summary = "Delete Trainer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable int id) {
        service.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}
