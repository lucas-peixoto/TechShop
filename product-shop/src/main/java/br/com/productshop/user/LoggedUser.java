package br.com.productshop.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoggedUser {

    public Optional<User> getLoggedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(authentication -> authentication.getPrincipal() instanceof User)
                .map(authentication -> (User) authentication.getPrincipal());
    }

    public boolean isLogged() {
        return getLoggedUser().isPresent();
    }

    public String getEmail() {
        return getLoggedUser().map(User::getUsername).orElseThrow(() -> new IllegalStateException("User not found"));
    }
}
