package com.calistenia.app.service.impl;

import com.calistenia.app.controller.dto.RoutineDTO;
import com.calistenia.app.repository.entities.RoutineEntity;
import com.calistenia.app.repository.RoutineRepository;
import com.calistenia.app.repository.enums.Difficulty;
import com.calistenia.app.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;

    @Override
    public RoutineEntity createRoutine(RoutineDTO routineDTO) {
        RoutineEntity routine = RoutineEntity.builder()
                .name(routineDTO.getName())
                .description(routineDTO.getDescription())
                .exercises(routineDTO.getExercises())
                .duration(routineDTO.getDuration())
                .difficulty(Difficulty.valueOf(routineDTO.getDifficulty()))
                .frequencyPerWeek(routineDTO.getFrequencyPerWeek())
                .build();

        return routineRepository.save(routine);
    }

    @Override
    public List<RoutineEntity> getAllRoutines() {
        return routineRepository.findAll();
    }

    @Override
    public RoutineEntity getRoutineById(Long id) {
        Optional<RoutineEntity> routine = routineRepository.findById(id);
        if (routine.isPresent()) {
            return routine.get();
        } else {
            throw new RuntimeException("Rutina no encontrada");
        }
    }

    @Override
    public RoutineEntity updateRoutine(Long id, RoutineDTO routineDTO) {
        RoutineEntity routine = getRoutineById(id);

        routine.setName(routineDTO.getName());
        routine.setDescription(routineDTO.getDescription());
        routine.setExercises(routineDTO.getExercises());
        routine.setDuration(routineDTO.getDuration());
        routine.setDifficulty(Difficulty.valueOf(routineDTO.getDifficulty()));
        routine.setFrequencyPerWeek(routineDTO.getFrequencyPerWeek());

        return routineRepository.save(routine);
    }

    @Override
    public void deleteRoutine(Long id) {
        RoutineEntity routine = getRoutineById(id);
        routineRepository.delete(routine);
    }
}



