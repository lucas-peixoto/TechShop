package br.com.authservice.registration;

import br.com.authservice.user.Role;
import jakarta.validation.constraints.*;

public record UserRegistrationRequest(@NotBlank String name, @Email @NotNull String email, @NotBlank String password,
                                      @NotNull Role role) {
}
