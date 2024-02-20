package br.com.authservice.user;

import br.com.authservice.exception.ValidationResult;
import br.com.authservice.registration.UserRegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ValidationResult validateForCreation(UserRegistrationRequest userRegistrationRequest) {
        ValidationResult validationResult = new ValidationResult();

        if (userRepository.existsByEmail(userRegistrationRequest.email())) {
            validationResult.addError("name", "User email '%s' already exists".formatted(userRegistrationRequest.name()));
        }

        return validationResult;
    }
}
