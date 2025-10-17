package br.com.gestao_cursos.modules.company.UseCase;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.gestao_cursos.exceptions.CompanyNotFoundException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;

@ExtendWith(MockitoExtension.class)
public class AuthCompanyUseCaseTest {
    @InjectMocks
    private AuthCompanyUseCase authCompanyUseCase;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("Should not be able to generate token with a valid company")
    public void should_not_be_able_to_generate_token_with_a_non_existent_company(){
        var authCompanyRequestDTO = AuthCompanyRequestDTO.builder()
            .username("COMPANY_TEST")
            .password("PASSWORD_TEST")
            .build();

        CompanyEntity company = CompanyEntity.builder()
            .id(UUID.randomUUID())
            .username(authCompanyRequestDTO.getUsername())
            .password(authCompanyRequestDTO.getPassword())
            .build();
        
        when(this.companyRepository.findByUsernameOrEmail(company.getUsername(), null))
            .thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.authCompanyUseCase.auth(authCompanyRequestDTO))
            .isInstanceOf(CompanyNotFoundException.class);
    }
}
