package br.com.authservice.login;

import jakarta.validation.constraints.NotBlank;

public record UserCredentials(@NotBlank String email, @NotBlank String password) {
}
