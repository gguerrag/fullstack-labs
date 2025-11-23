package com.example.users.controladores;

import com.example.users.entidades.User;
import com.example.users.servicios.UserService;
import com.example.users.dtos.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User u) {
        User created = userService.registerBasicUser(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean ok = userService.validarCredenciales(
                request.getUsername(),
                request.getPassword()
        );

        if (!ok) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        // Aquí podrías devolver un token, o por ahora un mensaje simple / el usuario
        return ResponseEntity.ok("Login correcto");
    }
}
