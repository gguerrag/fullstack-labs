package com.example.dtos;

import jakarta.validation.constraints.*;
import java.util.Set;

public record UpdateUserRequest(
  @NotBlank String displayName,
  Boolean enabled,
  Set<String> roles
) {}
