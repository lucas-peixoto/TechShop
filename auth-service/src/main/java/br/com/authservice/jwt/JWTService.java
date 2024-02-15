package br.com.authservice.jwt;

import br.com.authservice.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String SECRET;
    private long EXPIRATION_TIME = 86_400_000; // 1 day

    public String createToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role", user.getRole().name())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public String getEmail(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }
}
