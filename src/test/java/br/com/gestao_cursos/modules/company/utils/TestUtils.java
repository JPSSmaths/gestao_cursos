package br.com.gestao_cursos.modules.company.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TestUtils {
    private static String generateToken(UUID companyId, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expires_in = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create().withIssuer("cursos-programação")
            .withSubject(companyId.toString())
            .withExpiresAt(expires_in)
            .withClaim("roles", Arrays.asList("ROLE_COMPANY"))
            .sign(algorithm);
        
        return token;
    }
}
