package it.pkg.results.controller;

import it.pkg.results.entity.Resultado;
import it.pkg.results.service.ResultadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class ResultadoController {

    private final ResultadoService resultadoService;

    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @PostMapping
    public ResponseEntity<Resultado> crear(@RequestBody Resultado resultado) {
        return ResponseEntity.ok(resultadoService.crearResultado(resultado));
    }
    @GetMapping
    public ResponseEntity<List<Resultado>> obtenerTodos() {
        return ResponseEntity.ok(resultadoService.obtenerTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Resultado> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(resultadoService.obtenerPorId(id));
    }

    @GetMapping("/user/{usuarioId}")
    public ResponseEntity<List<Resultado>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(resultadoService.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/lab/{laboratorioId}")
    public ResponseEntity<List<Resultado>> obtenerPorLaboratorio(@PathVariable Long laboratorioId) {
        return ResponseEntity.ok(resultadoService.obtenerPorLaboratorio(laboratorioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultado> actualizar(@PathVariable Long id,
                                                @RequestBody Resultado resultado) {
        return ResponseEntity.ok(resultadoService.actualizar(id, resultado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resultadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
