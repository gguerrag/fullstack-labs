package com.example.users.controladores;

import com.example.users.dtos.CreateUserRequest;
import com.example.users.entidades.User;
import com.example.users.servicios.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> listar() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User obtener(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User create(@RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping("/{id}")
    public User actualizar(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("Usuario eliminado");
    }
}
