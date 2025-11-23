package com.example.labs.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CrearLaboratorioRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 200, message = "El nombre no puede superar 200 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 300, message = "La dirección no puede superar 300 caracteres")
    private String direccion;

    @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
    private String telefono;

    @Email(message = "El correo no tiene un formato válido")
    @Size(max = 100, message = "El correo no puede superar 100 caracteres")
    private String correoContacto;

    // true = "Y", false = "N"
    private Boolean activo = true;

    // Getters y setters

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoContacto() { return correoContacto; }
    public void setCorreoContacto(String correoContacto) { this.correoContacto = correoContacto; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
