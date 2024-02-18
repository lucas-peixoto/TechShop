package br.com.authservice.user;

import br.com.authservice.exception.ValidationResult;
import br.com.authservice.registration.UserRegistrationRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ValidationResult validateForCreation(UserRegistrationRequest userRegistrationRequest) {
        Map<String, String> errors = new HashMap<>();

        if (userRepository.existsByEmail(userRegistrationRequest.email())) {
            errors.put("email", "User email '%s' already exists".formatted(userRegistrationRequest.email()));
        }

        return errors.isEmpty() ? new ValidationResult.Success() : new ValidationResult.FieldErrors(errors);
    }
}
