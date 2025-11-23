package com.example.users.config;

import com.example.users.entidades.Role;
import com.example.users.repositorio.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepo;

    public DataSeeder(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (roleRepo.count() == 0) {
            roleRepo.save(new Role(null, "ADMIN"));
            roleRepo.save(new Role(null, "MANAGER"));
            roleRepo.save(new Role(null, "BASIC"));
        }
    }
}
