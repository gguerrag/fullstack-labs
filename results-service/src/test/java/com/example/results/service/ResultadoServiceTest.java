package com.example.results.service;

import com.example.results.entity.Resultado;
import com.example.results.exceptions.ResourceNotFoundException;
import com.example.results.repository.ResultadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultadoServiceTest {

    @Mock
    ResultadoRepository resultadoRepository;

    @InjectMocks
    ResultadoService resultadoService;

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
    void obtenerPorId_cuandoExiste_retornaResultado() {
        when(resultadoRepository.findById(1L)).thenReturn(Optional.of(sample(1L)));

        Resultado res = resultadoService.obtenerPorId(1L);

        assertNotNull(res);
        assertEquals(1L, res.getId());
        verify(resultadoRepository).findById(1L);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_lanzaNotFound() {
        when(resultadoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> resultadoService.obtenerPorId(99L));
        verify(resultadoRepository).findById(99L);
    }

    @Test
    void crearResultado_guardaEnRepo() {
        Resultado input = sample(null);
        Resultado saved = sample(5L);

        when(resultadoRepository.save(any(Resultado.class))).thenReturn(saved);

        Resultado res = resultadoService.crearResultado(input);

        assertEquals(5L, res.getId());

        ArgumentCaptor<Resultado> captor = ArgumentCaptor.forClass(Resultado.class);
        verify(resultadoRepository).save(captor.capture());
        assertEquals("Hemograma", captor.getValue().getTipoExamen());
    }

    @Test
    void actualizar_cuandoExiste_actualizaCampos() {
        Resultado existente = sample(1L);
        Resultado nuevo = sample(null);
        nuevo.setTipoExamen("Perfil Lipídico");

        when(resultadoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(resultadoRepository.save(any(Resultado.class))).thenAnswer(inv -> inv.getArgument(0));

        Resultado res = resultadoService.actualizar(1L, nuevo);

        assertEquals(1L, res.getId());
        assertEquals("Perfil Lipídico", res.getTipoExamen());
        verify(resultadoRepository).save(any(Resultado.class));
    }
}
