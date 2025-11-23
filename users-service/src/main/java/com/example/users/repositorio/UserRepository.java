package com.example.users.repositorio;

import com.example.users.entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import java.util.Optional;
import com.example.users.entidades.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByRut(String rut);
    

    @Query("select u from User u join u.roles r where r.name = :name")
    List<User> findByRoleName(@Param("name") String name);
}
