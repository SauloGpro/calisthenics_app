package com.calistenia.app.service;

import com.calistenia.app.controller.dto.ExerciseDTO;
import com.calistenia.app.repository.entities.ExerciseEntity;
import com.calistenia.app.repository.enums.BodyPart;
import com.calistenia.app.repository.enums.Difficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ExerciseService {
    ExerciseEntity addExercise(ExerciseEntity exercise);

    List<ExerciseEntity> getAllExercises();

    Page<ExerciseEntity> getAllExercises(Pageable pageable); // Añadimos la paginación

    ExerciseEntity getExerciseById(Long id);

    void deleteExercise(Long id);

    public ExerciseEntity addExerciseWithAuth(ExerciseDTO exerciseDTO, String token);

    void deleteExerciseWithAuth(Long id, String token);

    List<ExerciseEntity> filterExercisesByBodyPart(List<BodyPart> bodyParts);

    List<ExerciseEntity> filterExercisesByDifficulty(Difficulty difficulty);

    List<ExerciseEntity> filterExercisesByName(String name);

    List<ExerciseEntity> addExercisesWithAuth(List<ExerciseDTO> exerciseDTOs, String token);

}



