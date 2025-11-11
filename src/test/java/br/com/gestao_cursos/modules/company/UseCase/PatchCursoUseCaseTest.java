package br.com.gestao_cursos.modules.company.UseCase;

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

import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.curso.Active;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.PatchCursoDTO;

@ExtendWith(MockitoExtension.class)
public class PatchCursoUseCaseTest {
    @InjectMocks
    PatchCursoUseCase patchCursoUseCase;

    @Mock
    CursoRepository cursoRepository;

    @Test
    @DisplayName("Should not be able to update a non-existent course")
    public void should_not_be_able_update_a_non_existent_course() {
        CompanyEntity company = CompanyEntity.builder().id(UUID.randomUUID()).build();

        when(this.cursoRepository.findById(company.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            this.patchCursoUseCase.execute(company.getId(), null, company.getId());
        }).isInstanceOf(CursoNotFoundException.class);
    }

    @Test
    @DisplayName("Should be able to update a existent course")
    public void should_be_able_update_a_existent_course() {
        CompanyEntity company = CompanyEntity.builder().id(UUID.randomUUID()).build();

        PatchCursoDTO patchCursoDTO = PatchCursoDTO.builder()
                .active("ACTIVE")
                .build();
        
        CursoEntity course = CursoEntity.builder()
                .active(Enum.valueOf(Active.class, patchCursoDTO.active()))
                .companyId(company.getId())
                .build();
        
        CursoEntity courseUpdated = course;
        courseUpdated.setActive(Enum.valueOf(Active.class, "INACTIVE"));

        when(this.cursoRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(this.cursoRepository.save(course)).thenReturn(courseUpdated);

        this.patchCursoUseCase.execute(course.getId(), patchCursoDTO, company.getId());

        assertEquals(course, courseUpdated);
    }

    @Test
    @DisplayName("Should not be able to update a course from another company")
    public void should_not_be_able_update_a_course_from_another_company() {
        CompanyEntity company = CompanyEntity.builder().id(UUID.randomUUID()).build();
        CompanyEntity anotherCompany = CompanyEntity.builder().id(UUID.randomUUID()).build();

        PatchCursoDTO patchCursoDTO = PatchCursoDTO.builder()
                .active("ACTIVE")
                .build();

        CursoEntity course = CursoEntity.builder()
                .active(Enum.valueOf(Active.class, patchCursoDTO.active()))
                .companyId(company.getId())
                .build();

        when(this.cursoRepository.findById(course.getId())).thenReturn(Optional.of(course));

        assertThatThrownBy(() -> {
            this.patchCursoUseCase.execute(course.getId(), patchCursoDTO, anotherCompany.getId());
        }).isInstanceOf(CursoNotFoundException.class);
    }
}
