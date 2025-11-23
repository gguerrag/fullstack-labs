package com.example.repositorio;

import com.example.labs.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
}