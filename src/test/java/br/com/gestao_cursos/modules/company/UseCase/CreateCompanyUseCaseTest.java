package br.com.gestao_cursos.modules.company.UseCase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        try {
            
        } catch (Exception e) {
            
        }
    }
}
