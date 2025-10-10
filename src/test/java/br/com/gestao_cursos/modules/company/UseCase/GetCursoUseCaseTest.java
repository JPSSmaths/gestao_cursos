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

import br.com.gestao_cursos.exceptions.CompanyNotFoundException;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class GetCursoUseCaseTest {
    
    @InjectMocks
    private GetCursoUseCase getCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Should not be able list courses of a company that not exists")
    public void should_not_be_able_list_courses_of_a_company_that_not_exists(){
        UUID companyId = UUID.randomUUID();

        when(this.cursoRepository.findAllByCompanyId(companyId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            this.getCursoUseCase.execute(companyId);
        }).isInstanceOf(CompanyNotFoundException.class);
    }


}
