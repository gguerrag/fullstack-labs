package com.example.users.servicios;

import com.example.users.dtos.CreateUserRequest;
import com.example.users.entidades.Role;
import com.example.users.entidades.User;
import com.example.users.repositorio.RoleRepository;
import com.example.users.repositorio.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // -----------------------------------------------------
    // LISTAR USUARIOS
    // -----------------------------------------------------
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // -----------------------------------------------------
    // BUSCAR POR ID
    // -----------------------------------------------------
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // -----------------------------------------------------
    // CREAR USUARIO (CON ROLE)
    // -----------------------------------------------------
    public User create(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }

        if (userRepository.existsByRut(request.getRut())) {
            throw new RuntimeException("RUT ya registrado");
        }

        // Buscar rol (case insensitive)
        Role role = roleRepository.findByNameIgnoreCase(request.getRole())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + request.getRole()));

        User user = new User();
        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());
        user.setRut(request.getRut());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        return userRepository.save(user);
    }

    // -----------------------------------------------------
    // ACTUALIZAR
    // -----------------------------------------------------
    public User update(Long id, CreateUserRequest request) {
        User user = findById(id);

        user.setNombre(request.getNombre());
        user.setEmail(request.getEmail());
        user.setRut(request.getRut());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRole() != null) {
            Role role = roleRepository.findByNameIgnoreCase(request.getRole())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            user.setRole(role);
        }

        return userRepository.save(user);
    }

    // -----------------------------------------------------
    // ELIMINAR
    // -----------------------------------------------------
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // -----------------------------------------------------
    // LOGIN
    // -----------------------------------------------------
    public User validarCredenciales(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return user;
    }
}
