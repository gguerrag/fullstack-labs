package com.example.labs.repositorio;

import com.example.labs.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
}
