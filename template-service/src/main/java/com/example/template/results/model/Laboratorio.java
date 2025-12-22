package com.example.template.labs.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LABORATORIO")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Oracle 12c+ identity
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 200)
    private String nombre;

    @Column(name = "DIRECCION", length = 300)
    private String direccion;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Column(name = "CORREO_CONTACTO", length = 100)
    private String correoContacto;

    @Column(name = "ACTIVO", length = 1)
    private String activo = "Y";

    @Column(name = "FECHA_REGISTRO")
    private LocalDateTime fechaRegistro;

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoContacto() { return correoContacto; }
    public void setCorreoContacto(String correoContacto) { this.correoContacto = correoContacto; }

    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
