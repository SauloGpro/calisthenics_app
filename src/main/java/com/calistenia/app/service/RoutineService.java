package com.calistenia.app.service;

import com.calistenia.app.controller.dto.RoutineDTO;
import com.calistenia.app.repository.entities.RoutineEntity;

import java.util.List;

public interface RoutineService {

    // Método para crear una nueva rutina
    RoutineEntity createRoutine(RoutineDTO routineDTO);

    // Método para obtener todas las rutinas
    List<RoutineEntity> getAllRoutines();

    // Método para obtener una rutina por su ID
    RoutineEntity getRoutineById(Long id);

    // Método para actualizar una rutina
    RoutineEntity updateRoutine(Long id, RoutineDTO routineDTO);

    // Método para eliminar una rutina
    void deleteRoutine(Long id);
}

