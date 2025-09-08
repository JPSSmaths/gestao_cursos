package br.com.gestao_cursos.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTCompanyProvider {
    @Value("${security.jwt.secret.company}")
    private String secret;

    public DecodedJWT validateToken(String token){
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            var token_decoded = JWT.require(algorithm).build().verify(token);
            return token_decoded;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}