package br.com.authservice.verification;

import br.com.authservice.user.Role;
import br.com.authservice.user.User;

public record VerificationResponse(String email, Role role, boolean active) {
    public VerificationResponse(User user) {
        this(user.getEmail(), user.getRole(), user.isActive());
    }
}
