package br.com.authservice.login;

import jakarta.validation.constraints.*;

public record LoginRequest(@NotNull @Email String email, @NotBlank String password) {
}
