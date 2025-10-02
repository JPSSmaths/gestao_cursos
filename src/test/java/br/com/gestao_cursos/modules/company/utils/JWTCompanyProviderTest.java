package br.com.gestao_cursos.modules.company.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTCompanyProviderTest {
    private final static String secret = "COMPANY_123";
    
    public static DecodedJWT decodeToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        var token_decoded = JWT.require(algorithm).build().verify(token);
        return token_decoded;
    }
}
