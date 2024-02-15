package br.com.authservice.registration;

import br.com.authservice.user.User;
import br.com.authservice.user.UserRepository;
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

    public User register(UserRegistrationRequest request) {
        User user = new User(request.name(), request.email(), passwordEncoder.encode(request.password()), request.role());
        return userRepository.save(user);
    }
}
