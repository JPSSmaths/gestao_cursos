package br.com.gestao_cursos.modules.company.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    private final static String secret = "COMPANY_123";

    public static String generateToken(UUID companyId){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expires_in = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create().withIssuer("cursos-programação")
            .withSubject(companyId.toString())
            .withExpiresAt(expires_in)
            .withClaim("roles", Arrays.asList("ROLE_COMPANY"))
            .sign(algorithm);
        
        return token;
    }

    public static DecodedJWT decodeToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        var token_decoded = JWT.require(algorithm).build().verify(token);
        return token_decoded;
    }

    public static String objectToJSON(Object obj){
        try{
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException("Could not convert object to JSON string");
        }
    }
}
