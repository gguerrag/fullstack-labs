package com.example.users.servicios.impl;

import com.example.users.entidades.User;
import com.example.users.repositorio.UserRepository;
import com.example.users.servicios.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        // aquí podrías validar rut único, etc.
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(user.getNombre());
                    existing.setEmail(user.getEmail());
                    existing.setRut(user.getRut());
                    existing.setPassword(user.getPassword());
                    existing.setUsername(user.getUsername());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String emailOrUsername, String password) {
        User user = userRepository.findByEmail(emailOrUsername)
                .or(() -> userRepository.findByUsername(emailOrUsername))
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Para simplificar, comparamos texto plano
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return user;
    }
}
