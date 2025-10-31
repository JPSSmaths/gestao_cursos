package br.com.gestao_cursos.modules.company.UseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestao_cursos.exceptions.CompanyNotFoundException;
import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class GetCursoUseCaseTest {

    @InjectMocks
    private GetCursoUseCase getCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("Should not be able list courses of a company that not exists")
    public void should_not_be_able_list_courses_of_a_company_that_not_exists() {
        UUID companyId = UUID.randomUUID();

        when(this.companyRepository.existsById(companyId)).thenReturn(false);

        assertThatThrownBy(() -> {
            this.getCursoUseCase.execute(companyId);
        }).isInstanceOf(CompanyNotFoundException.class);
    }

    @Test
    @DisplayName("Should not be able list courses of a company that dont have courses")
    public void should_not_be_able_list_courses_of_a_company_that_dont_have_courses() {
        UUID company_id = UUID.randomUUID();

        when(this.companyRepository.existsById(company_id)).thenReturn(true);
        when(this.cursoRepository.findAllByCompanyId(company_id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> 
            this.getCursoUseCase.execute(company_id)
        ).isInstanceOf(CursoNotFoundException.class);
    }

    @Test
    @DisplayName("Should not be able list courses of a company that not exists")
    public void should_be_able_list_courses_of_a_company_that_exists() {
        UUID companyId = UUID.randomUUID();
        var course1 = CursoEntity.builder().name("COURSE_TEST_1").build();
        var course2 = CursoEntity.builder().name("COURSE_TEST_2").build();
        List<CursoEntity> courses = List.of(course1, course2);

        when(this.cursoRepository.findAllByCompanyId(companyId)).thenReturn(Optional.of(courses));

        var result = this.getCursoUseCase.execute(companyId);
        assertEquals(courses, result);
    }
}
