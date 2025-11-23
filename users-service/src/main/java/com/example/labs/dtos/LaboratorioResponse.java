package com.example.labs.dtos;

import java.time.LocalDateTime;

public class LaboratorioResponse {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correoContacto;
    // "Y" o "N" según Oracle
    private String activo;
    private LocalDateTime fechaRegistro;

    public LaboratorioResponse() {
    }

    public LaboratorioResponse(Long id, String nombre, String direccion, String telefono,
                               String correoContacto, String activo, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoContacto = correoContacto;
        this.activo = activo;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y setters

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
