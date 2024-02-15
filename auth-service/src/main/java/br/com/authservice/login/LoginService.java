package br.com.authservice.login;

import br.com.authservice.security.CustomUserDetails;
import br.com.authservice.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public final AuthenticationManager authenticationManager;

    public LoginService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public User login(UserCredentials credentials) {
        try {
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return ((CustomUserDetails) authentication.getPrincipal()).getUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
