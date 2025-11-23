package com.example.labs.servicios;

import com.example.labs.dtos.ActualizarLaboratorioRequest;
import com.example.labs.dtos.CrearLaboratorioRequest;
import com.example.labs.dtos.LaboratorioResponse;

import java.util.List;

public interface LaboratorioService {

    List<LaboratorioResponse> obtenerTodos();

    LaboratorioResponse obtenerPorId(Long id);

    LaboratorioResponse crear(CrearLaboratorioRequest request);

    LaboratorioResponse actualizar(Long id, ActualizarLaboratorioRequest request);

    void eliminar(Long id);
}
