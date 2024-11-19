package com.calistenia.app.service.impl;

import com.calistenia.app.repository.entities.ExerciseEntity;
import com.calistenia.app.repository.enums.BodyPart;
import com.calistenia.app.repository.enums.Difficulty;
import com.calistenia.app.repository.ExerciseRepository;
import com.calistenia.app.controller.dto.ExerciseDTO;
import com.calistenia.app.service.ExerciseService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final JwtConfig jwtConfig;

    private boolean isAdmin(String token) {
        Claims claims = jwtConfig.validateToken(token);
        String role = claims.get("role", String.class);
        return "ROLE_ADMIN".equals(role);
    }

    @Override
    public ExerciseEntity addExercise(ExerciseEntity exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<ExerciseEntity> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Page<ExerciseEntity> getAllExercises(Pageable pageable) {
        return exerciseRepository.findAll(pageable);
    }

    @Override
    public ExerciseEntity getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    public ExerciseEntity addExerciseWithAuth(ExerciseDTO exerciseDTO, String token) {
        if (isAdmin(token)) {
            ExerciseEntity exercise = new ExerciseEntity();
            exercise.setName(exerciseDTO.getName());
            exercise.setDescription(exerciseDTO.getDescription());
            exercise.setDifficulty(exerciseDTO.getDifficulty());
            exercise.setBodyParts(exerciseDTO.getBodyParts());
            exercise.setVideoUrl(exerciseDTO.getVideoUrl());
            exercise.setMusclesInvolved(exerciseDTO.getMusclesInvolved());
            exercise.setTechnicalTip(exerciseDTO.getTechnicalTip());
            exercise.setCommonMistake(exerciseDTO.getCommonMistake());
            return exerciseRepository.save(exercise);
        }
        throw new RuntimeException("No tienes permisos para agregar ejercicios.");
    }

    public void deleteExerciseWithAuth(Long id, String token) {
        if (isAdmin(token)) {
            exerciseRepository.deleteById(id);
        } else {
            throw new RuntimeException("No tienes permisos para eliminar ejercicios.");
        }
    }

    @Override
    public List<ExerciseEntity> filterExercisesByBodyPart(List<BodyPart> bodyParts) {
        return exerciseRepository.findAll().stream()
                .filter(exercise -> exercise.getBodyParts().stream().anyMatch(bodyParts::contains))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseEntity> filterExercisesByDifficulty(Difficulty difficulty) {
        return exerciseRepository.findAll().stream()
                .filter(exercise -> exercise.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseEntity> filterExercisesByName(String name) {
        return exerciseRepository.findAll().stream()
                .filter(exercise -> exercise.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseEntity> addExercisesWithAuth(List<ExerciseDTO> exerciseDTOs, String token) {
        if (isAdmin(token)) {
            List<ExerciseEntity> exercises = exerciseDTOs.stream().map(dto -> ExerciseEntity.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .difficulty(dto.getDifficulty())
                    .bodyParts(dto.getBodyParts())
                    .videoUrl(dto.getVideoUrl())
                    .musclesInvolved(dto.getMusclesInvolved())
                    .technicalTip(dto.getTechnicalTip())
                    .commonMistake(dto.getCommonMistake())
                    .build()
            ).collect(Collectors.toList());

            return exerciseRepository.saveAll(exercises);
        }
        throw new RuntimeException("No tienes permisos para agregar ejercicios.");
    }

}



