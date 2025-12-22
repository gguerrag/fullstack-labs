package com.example.template.results.repository;

import com.example.template.results.entity.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    List<Resultado> findByUsuarioId(Long usuarioId);

    List<Resultado> findByLaboratorioId(Long laboratorioId);
}
