package com.calistenia.app.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.calistenia.app.repository.enums.Difficulty;

import java.util.List;

@Entity
@Table(name = "routines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;            // Nombre de la rutina
    private String description;     // Descripción de la rutina

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> exercises; // Lista de ejercicios en la rutina

    private int duration;           // Duración de la rutina (en minutos)

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;  // Nivel de dificultad de la rutina

    private int frequencyPerWeek;   // Frecuencia de la rutina por semana
}


