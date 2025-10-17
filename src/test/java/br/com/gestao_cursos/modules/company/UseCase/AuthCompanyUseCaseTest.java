package br.com.gestao_cursos.modules.company.UseCase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;

@ExtendWith(MockitoExtension.class)
public class AuthCompanyUseCaseTest {
    @InjectMocks
    private AuthCompanyUseCase authCompanyUseCase;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("Should be able to generate token with a valid company")
    public void should_not_be_able_to_generate_token_with_a_non_existent_company(){
        
    }
}
