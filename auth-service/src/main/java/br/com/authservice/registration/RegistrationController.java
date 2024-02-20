package br.com.authservice.registration;

import br.com.authservice.user.Role;
import br.com.authservice.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody @Valid UserRegistrationRequest request) {
        User user = registrationService.register(request, Role.USER);
        return ResponseEntity.ok(new UserRegistrationResponse(user));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<UserRegistrationResponse> registerAdmin(@RequestBody @Valid UserRegistrationRequest request) {
        User user = registrationService.register(request, Role.ADMIN);
        return ResponseEntity.ok(new UserRegistrationResponse(user));
    }
}
