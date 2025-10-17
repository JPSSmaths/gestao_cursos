package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.gestao_cursos.exceptions.CompanyAlredyExistsException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.dto.CreateCompanyDTO;

@ExtendWith(MockitoExtension.class)
public class CreateCompanyUseCaseTest {
    
    @InjectMocks
    private CreateCompanyUseCase createCompanyUseCase;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Should not be able create a company that alredy exists")
    public void should_not_be_able_create_a_company_that_alredy_exists(){
        var company = CompanyEntity.builder().email("email@gmail.com").build();
        
        this.companyRepository.save(company);

        try {
            var companyExistent = CreateCompanyDTO.builder().email("email@gmail.com").build();
            this.createCompanyUseCase.create(companyExistent);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(CompanyAlredyExistsException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a company that not exists")
    public void should_be_able_to_create_a_company_that_not_exists(){
        var companyDTO = CreateCompanyDTO.builder()
            .username("USERNAME_TEST")
            .description("DESCRIPTION_TEST")
            .email("email@gmail.com")
            .password("PASSWORD_TEST")
            .build();
        
        var company = CompanyEntity.builder().username(companyDTO.getUsername())
            .id(UUID.randomUUID())
            .description(companyDTO.getDescription())
            .email(companyDTO.getEmail())
            .password(companyDTO.getPassword())
            .build();
        
        when(this.companyRepository.save(any(CompanyEntity.class))).thenReturn(company);
        
        var result = this.createCompanyUseCase.create(companyDTO);
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
