package com.calistenia.app.controller.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineDTO {

    private String name;            // Nombre de la rutina
    private String description;     // Descripción de la rutina
    private List<String> exercises; // Lista de ejercicios
    private int duration;           // Duración (en minutos)
    private String difficulty;      // Nivel de dificultad (usamos String si no queremos una enumeración)
    private int frequencyPerWeek;   // Frecuencia de la rutina por semana
}

