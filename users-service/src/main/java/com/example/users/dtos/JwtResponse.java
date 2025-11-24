package com.example.users.dtos;

public class JwtResponse {

    private String token;
    private Long id;
    private String nombre;
    private String email;
    private String username;

    public JwtResponse(String token, Long id, String nombre, String email, String username) {
        this.token = token;
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.username = username;
    }

    public String getToken() { return token; }
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
}
