package br.com.authservice.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationRequest(@NotBlank String name, @Email @NotNull String email, @NotBlank String password) {
}
