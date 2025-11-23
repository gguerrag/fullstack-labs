package com.example.labs.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.labs.model.DetalleOrden;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {

}
