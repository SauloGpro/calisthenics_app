package com.calistenia.app.repository.entities;

import com.calistenia.app.repository.enums.BodyPart;
import com.calistenia.app.repository.enums.Difficulty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;         // Nombre del ejercicio

    @Column(length = 1000) // Aumentamos el tamaño de la columna para la descripción
    private String description;  // Descripción del ejercicio

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;   // Dificultad del ejercicio, ahora es un enum

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<BodyPart> bodyParts; // Partes del cuerpo involucradas, ahora es una lista de enums

    private String videoUrl;     // URL del video explicativo

    private String musclesInvolved; // Músculos implicados
    private String technicalTip;    // Tip técnico
    private String commonMistake;   // Error común
}



