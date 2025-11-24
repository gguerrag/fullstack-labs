package com.example.users.servicios;

import com.example.users.entidades.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User saveUser(User user);

    Optional<User> getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User login(String emailOrUsername, String password);
}
