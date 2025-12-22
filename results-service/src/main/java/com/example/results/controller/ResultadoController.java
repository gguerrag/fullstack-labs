package com.example.results.controller;

import com.example.results.entity.Resultado;
import com.example.results.service.ResultadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultadoController {

    private static final Logger log = LoggerFactory.getLogger(ResultadoController.class);

    private final ResultadoService resultadoService;

    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @PostMapping
    public ResponseEntity<Resultado> crear(@RequestBody Resultado resultado) {
        log.info("POST /api/results usuarioId={} labId={} tipo={}",
                resultado.getUsuarioId(), resultado.getLaboratorioId(), resultado.getTipoExamen());

        Resultado created = resultadoService.crearResultado(resultado);

        // 201 Created
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Resultado>> obtenerTodos() {
        log.info("GET /api/results");
        return ResponseEntity.ok(resultadoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultado> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/results/{}", id);
        return ResponseEntity.ok(resultadoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultado> actualizar(@PathVariable Long id, @RequestBody Resultado resultado) {
        log.info("PUT /api/results/{}", id);
        return ResponseEntity.ok(resultadoService.actualizar(id, resultado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/results/{}", id);
        resultadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
