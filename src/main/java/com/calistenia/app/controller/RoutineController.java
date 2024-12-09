package com.calistenia.app.controller;

import com.calistenia.app.controller.dto.RoutineDTO;
import com.calistenia.app.repository.entities.RoutineEntity;
import com.calistenia.app.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping
    public ResponseEntity<RoutineEntity> createRoutine(@RequestBody RoutineDTO routineDTO) {
        RoutineEntity newRoutine = routineService.createRoutine(routineDTO);
        return ResponseEntity.ok(newRoutine);
    }

    @GetMapping
    public ResponseEntity<List<RoutineEntity>> getAllRoutines() {
        List<RoutineEntity> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineEntity> getRoutineById(@PathVariable Long id) {
        RoutineEntity routine = routineService.getRoutineById(id);
        return ResponseEntity.ok(routine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineEntity> updateRoutine(@PathVariable Long id, @RequestBody RoutineDTO routineDTO) {
        RoutineEntity updatedRoutine = routineService.updateRoutine(id, routineDTO);
        return ResponseEntity.ok(updatedRoutine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }
}


