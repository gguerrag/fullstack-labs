package com.example.labs.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDEN_EXAMEN")
public class OrdenExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PACIENTE_NOMBRE", nullable = false, length = 200)
    private String pacienteNombre;

    @Column(name = "PACIENTE_RUN", length = 20)
    private String pacienteRun;

    @Column(name = "MEDICO", length = 200)
    private String medico;

    @Column(name = "FECHA_ORDEN")
    private LocalDateTime fechaOrden;

    @Column(name = "TOTAL", precision = 12, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "ordenExamen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrden> detalles = new ArrayList<>();


    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String pacienteNombre) { this.pacienteNombre = pacienteNombre; }

    public String getPacienteRun() { return pacienteRun; }
    public void setPacienteRun(String pacienteRun) { this.pacienteRun = pacienteRun; }

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public LocalDateTime getFechaOrden() { return fechaOrden; }
    public void setFechaOrden(LocalDateTime fechaOrden) { this.fechaOrden = fechaOrden; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public List<DetalleOrden> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleOrden> detalles) { this.detalles = detalles; }
}
