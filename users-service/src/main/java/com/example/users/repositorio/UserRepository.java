package com.example.users.repositorio;

import com.example.users.entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByRut(String rut);

    boolean existsByEmail(String email);   // requerido por UserService

    boolean existsByRut(String rut);       // requerido por UserService
}
