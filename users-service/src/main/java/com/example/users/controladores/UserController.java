package com.example.users.controladores;

import com.example.users.entidades.User;
import com.example.users.servicios.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    // GET ONE
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // CREATE
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    // LOGIN
    @PostMapping("/auth/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {
        return userService.validarCredenciales(email, password);
    }
}
