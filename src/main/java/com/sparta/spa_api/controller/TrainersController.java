package com.sparta.spa_api.controller;

import com.sparta.spa_api.dtos.CourseDTO;
import com.sparta.spa_api.dtos.TrainersDTO;
import com.sparta.spa_api.entities.Course;
import com.sparta.spa_api.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/trainers")
public class TrainersController {

    private final TrainerService service;

    public TrainersController(TrainerService service) {
        this.service = service;
    }

    @Operation(summary = "Get Trainers by ID", description = "Retrieve the trainer.")
    @GetMapping("/{id}")
    public ResponseEntity<TrainersDTO> getTrainer(@PathVariable int id) {
        TrainersDTO trainer = service.getTrainer(id);

        if(trainer != null) {
            return ResponseEntity.status(200).body(trainer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get Trainers Course ID", description = "Retrieve the trainer's course.")
    @GetMapping("/{id}/courses/")
    public ResponseEntity<CourseDTO> getTraineresCourse(@PathVariable int id) {
        CourseDTO Course = service.getCourse(id);

        if(Course != null) {
            return ResponseEntity.status(201).body(Course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Works
    @Operation(summary = "Delete Trainers by ID", description = "Delete a trainer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteTrainerById(@PathVariable int id) {
        try {
            service.deleteTrainer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update my course name", description = "Retrieve the trainer's course and update's the name")
    @PutMapping("{id}/courses/setCourseName")
    public ResponseEntity<CourseDTO> updateCourseName(@PathVariable int id, @RequestBody String newCourseName) {
        CourseDTO Course = service.getCourse(id);

        try {
            Course.setCourse_name(newCourseName);
            return ResponseEntity.status(201).body(Course);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update my trainer name", description = "Update's the trainers name.")
    @PutMapping( "/name/{id}")
    public ResponseEntity<TrainersDTO> updateTrainerName(@PathVariable int id, @RequestBody String newTrainerName) {
        try {
            TrainersDTO trainer = service.setTrainerName(id, newTrainerName);
            return ResponseEntity.status(201).body(trainer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update my trainer id", description = "Update's the trainers id.")
    @PutMapping( "/{id}")
    public ResponseEntity<TrainersDTO> updateTrainerId(@RequestBody TrainersDTO trainerDTO, @PathVariable Integer newID) {
        try {
            TrainersDTO updatedTrainer = service.setTrainerId(trainerDTO, newID);
            return ResponseEntity.status(201).body(updatedTrainer);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
