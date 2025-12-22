package com.example.results.service;

import com.example.results.entity.Resultado;
import com.example.results.exceptions.ResourceNotFoundException;
import com.example.results.repository.ResultadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoService {

    private static final Logger log = LoggerFactory.getLogger(ResultadoService.class);

    private final ResultadoRepository resultadoRepository;

    public ResultadoService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public Resultado crearResultado(Resultado resultado) {
        log.info("Creando resultado usuarioId={} labId={} tipo={}",
                resultado.getUsuarioId(), resultado.getLaboratorioId(), resultado.getTipoExamen());
        return resultadoRepository.save(resultado);
    }

    public List<Resultado> obtenerTodos() {
        log.info("Listando resultados");
        return resultadoRepository.findAll();
    }

    public Resultado obtenerPorId(Long id) {
        log.info("Buscando resultado id={}", id);
        return resultadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado no encontrado con id: " + id));
    }

    public Resultado actualizar(Long id, Resultado nuevo) {
        log.info("Actualizando resultado id={}", id);

        Resultado existente = resultadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado no encontrado con id: " + id));

        // Ajusta estos campos si tu entidad tiene otros
        existente.setUsuarioId(nuevo.getUsuarioId());
        existente.setLaboratorioId(nuevo.getLaboratorioId());
        existente.setTipoExamen(nuevo.getTipoExamen());
        existente.setValorResultado(nuevo.getValorResultado());
        existente.setUnidad(nuevo.getUnidad());
        existente.setEstado(nuevo.getEstado());
        existente.setFechaResultado(nuevo.getFechaResultado());

        return resultadoRepository.save(existente);
    }

    public void eliminar(Long id) {
        log.info("Eliminando resultado id={}", id);

        Resultado existente = resultadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado no encontrado con id: " + id));

        resultadoRepository.delete(existente);
    }
}
