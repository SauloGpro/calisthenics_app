package com.calistenia.app.controller;

import com.calistenia.app.controller.dto.ExerciseDTO;
import com.calistenia.app.repository.entities.ExerciseEntity;
import com.calistenia.app.repository.enums.BodyPart;
import com.calistenia.app.repository.enums.Difficulty;
import com.calistenia.app.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<?> addExercises(@RequestBody List<ExerciseDTO> exerciseDTOs, @RequestHeader("Authorization") String token) {
        try {
            List<ExerciseEntity> savedExercises = exerciseService.addExercisesWithAuth(exerciseDTOs, token.replace("Bearer ", ""));
            return ResponseEntity.ok(savedExercises);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<Page<ExerciseDTO>> getAllExercises(Pageable pageable) {
        Page<ExerciseDTO> exerciseDTOs = exerciseService.getAllExercises(pageable).map(exercise ->
                new ExerciseDTO(
                        exercise.getName(),
                        exercise.getDescription(),
                        exercise.getDifficulty(),
                        exercise.getBodyParts(),
                        exercise.getVideoUrl(),
                        exercise.getMusclesInvolved(),
                        exercise.getTechnicalTip(),
                        exercise.getCommonMistake()
                )
        );
        return ResponseEntity.ok(exerciseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable Long id) {
        ExerciseEntity exercise = exerciseService.getExerciseById(id);
        if (exercise != null) {
            ExerciseDTO exerciseDTO = new ExerciseDTO(
                    exercise.getName(),
                    exercise.getDescription(),
                    exercise.getDifficulty(),
                    exercise.getBodyParts(),
                    exercise.getVideoUrl(),
                    exercise.getMusclesInvolved(),
                    exercise.getTechnicalTip(),
                    exercise.getCommonMistake()
            );
            return ResponseEntity.ok(exerciseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        try {
            exerciseService.deleteExerciseWithAuth(id, token.replace("Bearer ", ""));
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ExerciseDTO>> filterExercisesByBodyPart(@RequestParam List<BodyPart> bodyParts) {
        List<ExerciseDTO> exerciseDTOs = exerciseService.filterExercisesByBodyPart(bodyParts).stream().map(exercise ->
                new ExerciseDTO(
                        exercise.getName(),
                        exercise.getDescription(),
                        exercise.getDifficulty(),
                        exercise.getBodyParts(),
                        exercise.getVideoUrl(),
                        exercise.getMusclesInvolved(),
                        exercise.getTechnicalTip(),
                        exercise.getCommonMistake()
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(exerciseDTOs);
    }

    @GetMapping("/filter/difficulty")
    public ResponseEntity<List<ExerciseDTO>> filterExercisesByDifficulty(@RequestParam Difficulty difficulty) {
        List<ExerciseDTO> exerciseDTOs = exerciseService.filterExercisesByDifficulty(difficulty).stream().map(exercise ->
                new ExerciseDTO(
                        exercise.getName(),
                        exercise.getDescription(),
                        exercise.getDifficulty(),
                        exercise.getBodyParts(),
                        exercise.getVideoUrl(),
                        exercise.getMusclesInvolved(),
                        exercise.getTechnicalTip(),
                        exercise.getCommonMistake()
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(exerciseDTOs);
    }


    @GetMapping("/filter/name")
    public ResponseEntity<List<ExerciseDTO>> filterExercisesByName(@RequestParam String name) {
        List<ExerciseDTO> exerciseDTOs = exerciseService.filterExercisesByName(name).stream().map(exercise ->
                new ExerciseDTO(
                        exercise.getName(),
                        exercise.getDescription(),
                        exercise.getDifficulty(),
                        exercise.getBodyParts(),
                        exercise.getVideoUrl(),
                        exercise.getMusclesInvolved(),
                        exercise.getTechnicalTip(),
                        exercise.getCommonMistake()
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(exerciseDTOs);
    }
}





