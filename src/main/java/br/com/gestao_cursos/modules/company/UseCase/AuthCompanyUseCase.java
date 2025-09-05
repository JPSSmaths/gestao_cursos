package br.com.gestao_cursos.modules.company.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;

@Service
public class AuthCompanyUseCase {
    @Value("${security.jwt.secret.company}")
    private String secret;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void auth(AuthCompanyRequestDTO authCompanyRequestDTO){
        
    }
}
