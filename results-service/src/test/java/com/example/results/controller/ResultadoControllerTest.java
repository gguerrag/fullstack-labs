package com.example.results.controller;

import com.example.results.entity.Resultado;
import com.example.results.exceptions.ResourceNotFoundException;
import com.example.results.service.ResultadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResultadoController.class)
class ResultadoControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockBean ResultadoService resultadoService;

    private Resultado sample(Long id) {
        Resultado r = new Resultado();
        r.setId(id);
        r.setUsuarioId(10L);
        r.setLaboratorioId(20L);
        r.setTipoExamen("Hemograma");
        r.setValorResultado("13.5");
        r.setUnidad("g/dL");
        r.setEstado("LIBERADO");
        r.setFechaResultado(LocalDateTime.now());
        return r;
    }

    @Test
    void getPorId_ok() throws Exception {
        when(resultadoService.obtenerPorId(1L)).thenReturn(sample(1L));

        mockMvc.perform(get("/api/results/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getPorId_notFound() throws Exception {
        when(resultadoService.obtenerPorId(99L))
                .thenThrow(new ResourceNotFoundException("no"));

        mockMvc.perform(get("/api/results/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTodos_ok() throws Exception {
        when(resultadoService.obtenerTodos()).thenReturn(List.of(sample(1L), sample(2L)));

        mockMvc.perform(get("/api/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void post_crear_devuelve201() throws Exception {
        when(resultadoService.crearResultado(any(Resultado.class))).thenReturn(sample(5L));

        mockMvc.perform(post("/api/results")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sample(null))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5));
    }
}
