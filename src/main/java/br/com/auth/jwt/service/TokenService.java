package br.com.auth.jwt.service;

import br.com.auth.jwt.model.User;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    public String gerarToken(User user) {
        return JWT.create().withIssuer("Products")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(1).toInstant(ZoneOffset.of("-03:00"))))
                .sign(Algorithm.HMAC256("secret"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secret"))
                .withIssuer("Products")
                .build().verify(token)
                .getSubject();
    }
}
