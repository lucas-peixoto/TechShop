package br.com.authservice.registration;

import br.com.authservice.user.Role;
import br.com.authservice.user.User;
import br.com.authservice.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegistrationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User register(UserRegistrationRequest request, Role role) {
        User user = new User(request.name(), request.email(), passwordEncoder.encode(request.password()), role);
        return userRepository.save(user);
    }
}
