package br.com.gestao_cursos.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTCompanyProvider {
    @Value("${security.jwt.secret.company}")
    private String secret;

    public void vlaidateToken(String token){
        token = token.replace("Bearer ", "");
    }
}