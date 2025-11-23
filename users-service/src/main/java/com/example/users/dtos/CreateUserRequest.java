package com.example.dtos;
import jakarta.validation.constraints.*;
import java.util.Set;
public record CreateUserRequest(
    @Email @NotBlank String email,
    @NotBlank String displayName,
    @Size(min=8, max=64) String password,
    Set<String> roles
) {}
