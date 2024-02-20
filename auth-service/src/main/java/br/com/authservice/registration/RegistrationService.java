package br.com.authservice.registration;

import br.com.authservice.user.Role;
import br.com.authservice.user.User;
import br.com.authservice.user.UserRepository;
import br.com.authservice.user.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserValidator userValidator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public User register(UserRegistrationRequest request, Role role) {
        userValidator.validateForCreation(request).throwIfInvalid();
        User user = new User(request.name(), request.email(), passwordEncoder.encode(request.password()), role);
        return userRepository.save(user);
    }
}
