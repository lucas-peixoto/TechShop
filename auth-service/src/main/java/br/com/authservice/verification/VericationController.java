package br.com.authservice.verification;

import br.com.authservice.jwt.JWTService;
import br.com.authservice.user.User;
import br.com.authservice.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VericationController {

    private final JWTService jwtService;
    private final UserService userService;

    public VericationController(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/verify")
    public ResponseEntity<VerificationResponse> verify(@RequestBody VerificationRequest request) {
        User user = userService.getByEmail(jwtService.getEmail(request.token()));

        return ResponseEntity.ok(new VerificationResponse(user));
    }
}
