package br.com.authservice.verification;

import br.com.authservice.jwt.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VericationController {

    private final JWTService jwtService;

    public VericationController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/verify")
    public ResponseEntity<VerificationResponse> verify(@RequestBody VerificationRequest request) {
        boolean isValid = jwtService.isValid(request.token());
        return ResponseEntity.ok(new VerificationResponse(isValid));
    }
}
