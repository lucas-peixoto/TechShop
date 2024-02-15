package br.com.authservice.registration;

import br.com.authservice.user.Role;
import br.com.authservice.user.User;

public record UserRegistrationResponse(String name, String email, Role role) {
    public UserRegistrationResponse(User user) {
        this(user.getName(), user.getEmail(), user.getRole());
    }
}
