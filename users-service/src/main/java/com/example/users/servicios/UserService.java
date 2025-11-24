package com.example.users.servicios;

import com.example.users.entidades.User;
import com.example.users.repositorio.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // GET ALL
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // GET ONE
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // CREATE
    public User create(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }

        if (userRepository.existsByRut(user.getRut())) {
            throw new RuntimeException("RUT ya registrado");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // UPDATE
    public User update(Long id, User request) {
        User user = findById(id);

        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());
        user.setRut(request.getRut());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(user);
    }

    // DELETE
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // LOGIN
    public User validarCredenciales(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return user;
    }
}
