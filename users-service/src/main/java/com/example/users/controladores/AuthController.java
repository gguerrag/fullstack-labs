package com.example.users.controladores;

import com.example.users.dtos.JwtResponse;
import com.example.users.dtos.LoginRequest;
import com.example.users.entidades.User;
import com.example.users.servicios.JwtService;
import com.example.users.servicios.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        User user = userService.login(request.getEmailOrUsername(), request.getPassword());
        String token = jwtService.generateToken(user);

        JwtResponse response = new JwtResponse(
                token,
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getUsername()
        );

        return ResponseEntity.ok(response);
    }
}
