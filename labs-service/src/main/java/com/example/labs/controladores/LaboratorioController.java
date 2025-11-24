package com.example.labs.controladores;

import com.example.labs.dtos.ActualizarLaboratorioRequest;
import com.example.labs.dtos.CrearLaboratorioRequest;
import com.example.labs.dtos.LaboratorioResponse;
import com.example.labs.servicios.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labs/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @GetMapping
    public ResponseEntity<List<LaboratorioResponse>> obtenerTodos() {
        return ResponseEntity.ok(laboratorioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(laboratorioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<LaboratorioResponse> crear(@Valid @RequestBody CrearLaboratorioRequest request) {
        LaboratorioResponse creado = laboratorioService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioResponse> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody ActualizarLaboratorioRequest request) {
        LaboratorioResponse actualizado = laboratorioService.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        laboratorioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
