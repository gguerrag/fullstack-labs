package com.example.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.users")
@EnableJpaRepositories(basePackages = "com.example.users.repositorio")
@EntityScan(basePackages = "com.example.users.entidades")
public class UsersServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }
}
