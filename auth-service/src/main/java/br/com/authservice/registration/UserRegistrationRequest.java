package br.com.authservice.registration;

import jakarta.validation.constraints.*;

public record UserRegistrationRequest(@NotBlank String name, @Email @NotNull String email, @NotBlank String password) {
}
