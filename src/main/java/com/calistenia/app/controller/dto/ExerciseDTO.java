package com.calistenia.app.controller.dto;

import com.calistenia.app.repository.enums.BodyPart;
import com.calistenia.app.repository.enums.Difficulty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseDTO {
    private String name;         // Nombre del ejercicio
    private String description;  // Descripción del ejercicio
    private Difficulty difficulty;   // Dificultad del ejercicio (ahora enum)
    private List<BodyPart> bodyParts; // Partes del cuerpo involucradas (ahora enum)
    private String videoUrl;     // URL del video explicativo

    private String musclesInvolved; // Músculos implicados
    private String technicalTip;    // Tip técnico
    private String commonMistake;   // Error común
}


