package it.pkg.results.service;

import it.pkg.results.entity.Resultado;
import it.pkg.results.repository.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoService {

    private final ResultadoRepository resultadoRepository;

    public ResultadoService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public Resultado crearResultado(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    public Resultado obtenerPorId(Long id) {
        return resultadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
    }
    
    public List<Resultado> obtenerTodos() {
    return resultadoRepository.findAll();
    
}
    public List<Resultado> obtenerPorUsuario(Long usuarioId) {
        return resultadoRepository.findByUsuarioId(usuarioId);
    }

    public List<Resultado> obtenerPorLaboratorio(Long laboratorioId) {
        return resultadoRepository.findByLaboratorioId(laboratorioId);
    }

    public Resultado actualizar(Long id, Resultado nuevoResultado) {
        Resultado existente = obtenerPorId(id);

        existente.setUsuarioId(nuevoResultado.getUsuarioId());
        existente.setLaboratorioId(nuevoResultado.getLaboratorioId());
        existente.setTipoExamen(nuevoResultado.getTipoExamen());
        existente.setValorResultado(nuevoResultado.getValorResultado());
        existente.setUnidad(nuevoResultado.getUnidad());
        existente.setEstado(nuevoResultado.getEstado());
        existente.setFechaResultado(nuevoResultado.getFechaResultado());

        return resultadoRepository.save(existente);
    }

    public void eliminar(Long id) {
        resultadoRepository.deleteById(id);
    }
}
