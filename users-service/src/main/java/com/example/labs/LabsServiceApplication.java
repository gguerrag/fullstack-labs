package com.example.labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
  scanBasePackages = {"com.example.labs"} // SOLO beans de labs
)
@EntityScan(basePackages = "com.example.labs.model")
@EnableJpaRepositories(basePackages = "com.example.labs.repository")
public class LabsServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(LabsServiceApplication.class, args);
  }
}
