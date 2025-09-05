package br.com.gestao_cursos.modules.company.UseCase;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;

@Service
public class AuthCompanyUseCase {
    @Value("${security.jwt.secret.company}")
    private String secret;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;
    
    public String auth(AuthCompanyRequestDTO authCompanyRequestDTO){
        var company = this.companyRepository.findByUsernameOrEmail(authCompanyRequestDTO.getUsername(), null)
        .orElseThrow(() -> new UserFoundException("Usuário não cadastrado"));

        if(!this.passwordEncoder.matches(authCompanyRequestDTO.getPassword(), company.getPassword())){
            throw new UserFoundException("Senha inválida");
        }

        var expiresAt = Instant.now().plus(Duration.ofHours(2));

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
            .withIssuer("cursos-programação")
            .withSubject(company.getId().toString())
            .withClaim("roles", Arrays.asList("ROLE_COMPANY"))
            .withExpiresAt(expiresAt)
            .sign(algorithm);
        
            return token;
    }

}