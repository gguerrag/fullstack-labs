package com.example.labs.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DETALLE_ORDEN")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DETALLE")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDEN", nullable = false)
    private OrdenExamen ordenExamen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EXAMEN", nullable = false)
    private Examen examen;

    @Column(name = "RESULTADO", length = 500)
    private String resultado;

    @Column(name = "FECHA_RESULTADO")
    private LocalDateTime fechaResultado;

    @Column(name = "OBSERVACIONES", length = 500)
    private String observaciones;

    public DetalleOrden() {
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdenExamen getOrdenExamen() {
        return ordenExamen;
    }

    public void setOrdenExamen(OrdenExamen ordenExamen) {
        this.ordenExamen = ordenExamen;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public LocalDateTime getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDateTime fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
