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

import br.com.gestao_cursos.exceptions.CompanyNotFoundException;
import br.com.gestao_cursos.exceptions.InvalidPasswordException;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;
import br.com.gestao_cursos.modules.company.dto.AuthCompanyResponseDTO;

@Service
public class AuthCompanyUseCase {
    private final String secret;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    @Autowired
    public AuthCompanyUseCase(
            @Value("${security.jwt.secret.company}") String secret,
            PasswordEncoder passwordEncoder,
            CompanyRepository companyRepository) {
        this.secret = secret;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
    }

    public AuthCompanyResponseDTO auth(AuthCompanyRequestDTO authCompanyRequestDTO) {
        var company = this.companyRepository.findByUsernameOrEmail(authCompanyRequestDTO.getUsername(), null)
                .orElseThrow(() -> new CompanyNotFoundException());

        if (!this.passwordEncoder.matches(authCompanyRequestDTO.getPassword(), company.getPassword())) {
            throw new InvalidPasswordException();
        }

        var expires_in = Instant.now().plus(Duration.ofHours(2));

        Algorithm algorithm = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withIssuer("cursos-programação")
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("ROLE_COMPANY"))
                .withExpiresAt(expires_in)
                .sign(algorithm);

        AuthCompanyResponseDTO authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .acess_token(token)
                .expires_in(expires_in.toEpochMilli())
                .build();

        return authCompanyResponseDTO;
    }

}