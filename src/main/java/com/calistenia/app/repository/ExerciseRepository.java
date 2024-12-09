package com.calistenia.app.repository;

import com.calistenia.app.repository.entities.ExerciseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    Page<ExerciseEntity> findAll(Pageable pageable); // Aquí implementamos la paginación
}


