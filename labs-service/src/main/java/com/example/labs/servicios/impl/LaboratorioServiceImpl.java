package com.example.labs.servicios.impl;

import com.example.labs.dtos.ActualizarLaboratorioRequest;
import com.example.labs.dtos.CrearLaboratorioRequest;
import com.example.labs.dtos.LaboratorioResponse;
import com.example.labs.exceptions.ResourceNotFoundException;
import com.example.labs.model.Laboratorio;
import com.example.labs.repositorio.LaboratorioRepository;
import com.example.labs.servicios.LaboratorioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LaboratorioServiceImpl implements LaboratorioService {

    private static final Logger log = LoggerFactory.getLogger(LaboratorioServiceImpl.class);

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioServiceImpl(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    @Override
    public List<LaboratorioResponse> obtenerTodos() {
        log.info("Obteniendo todos los laboratorios");
        return laboratorioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public LaboratorioResponse obtenerPorId(Long id) {
        log.info("Buscando laboratorio con id {}", id);
        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratorio no encontrado con id: " + id));

        return toResponse(laboratorio);
    }

    @Override
    public LaboratorioResponse crear(CrearLaboratorioRequest request) {

        log.info("Creando laboratorio {}", request.getNombre());

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNombre(request.getNombre());
        laboratorio.setDireccion(request.getDireccion());
        laboratorio.setTelefono(request.getTelefono());
        laboratorio.setCorreoContacto(request.getCorreoContacto());

        laboratorio.setActivo(request.getActivo() ? "Y" : "N");
        laboratorio.setFechaRegistro(LocalDateTime.now());

        laboratorio = laboratorioRepository.save(laboratorio);

        return toResponse(laboratorio);
    }

    @Override
    public LaboratorioResponse actualizar(Long id, ActualizarLaboratorioRequest request) {

        log.info("Actualizando laboratorio {}", id);

        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratorio no encontrado con id: " + id));

        laboratorio.setNombre(request.getNombre());
        laboratorio.setDireccion(request.getDireccion());
        laboratorio.setTelefono(request.getTelefono());
        laboratorio.setCorreoContacto(request.getCorreoContacto());

        if (request.getActivo() != null) {
            laboratorio.setActivo(request.getActivo() ? "Y" : "N");
        }

        laboratorio = laboratorioRepository.save(laboratorio);

        return toResponse(laboratorio);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando laboratorio {}", id);

        if (!laboratorioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Laboratorio no encontrado con id: " + id);
        }

        laboratorioRepository.deleteById(id);
    }

    private LaboratorioResponse toResponse(Laboratorio laboratorio) {
        return new LaboratorioResponse(
                laboratorio.getId(),
                laboratorio.getNombre(),
                laboratorio.getDireccion(),
                laboratorio.getTelefono(),
                laboratorio.getCorreoContacto(),
                laboratorio.getActivo(),        // "Y" o "N"
                laboratorio.getFechaRegistro()
        );
    }
}
