package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestao_cursos.exceptions.CompanyAlredyExistsException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;

@ExtendWith(MockitoExtension.class)
public class CreateCompanyUseCaseTest {
    
    @InjectMocks
    private CreateCompanyUseCase createCompanyUseCase;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("Should not be able create a company that alredy exists")
    public void should_not_be_able_create_a_company_that_alredy_exists(){
        var company = CompanyEntity.builder()
            .id(UUID.randomUUID())
            .build();
        
        this.companyRepository.save(company);

        try {
            var companyExistent = company;
            this.companyRepository.save(companyExistent);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(CompanyAlredyExistsException.class);
        }
    }
}
