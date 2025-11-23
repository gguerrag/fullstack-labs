package com.example.users.controladores;

import com.example.users.entidades.User;
import com.example.users.repositorio.UserRepository;
import com.example.users.servicios.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepo;

    public UserController(UserService userService, UserRepository userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<User> all() { return userService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> one(@PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User u) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User u) {
        return userRepo.findById(id).map(db -> {
            if (u.getFirstName()!=null) db.setFirstName(u.getFirstName());
            if (u.getLastName()!=null) db.setLastName(u.getLastName());
            if (u.getEmail()!=null) db.setEmail(u.getEmail());
            if (u.getRut()!=null) db.setRut(u.getRut());
            return ResponseEntity.ok(userRepo.save(db));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // consultas “complejas”
    @GetMapping("/search")
    public ResponseEntity<User> byEmail(@RequestParam String email) {
        return userRepo.findByEmail(email).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-role/{name}")
    public List<User> byRole(@PathVariable String name) {
        return userRepo.findByRoleName(name);
    }
}
