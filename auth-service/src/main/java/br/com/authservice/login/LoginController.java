package br.com.authservice.login;

import br.com.authservice.jwt.JWTService;
import br.com.authservice.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final JWTService jwtService;
    private final LoginService loginService;

    public LoginController(JWTService jwtService, LoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserCredentials credentials) {
        User user = loginService.login(credentials);
        String token = jwtService.createToken(user);
        return ResponseEntity.ok(token);
    }
}
