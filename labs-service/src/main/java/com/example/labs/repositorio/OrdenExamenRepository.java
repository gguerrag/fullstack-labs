package com.example.labs.repositorio;

import com.example.labs.model.OrdenExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenExamenRepository extends JpaRepository<OrdenExamen, Long> {
}
