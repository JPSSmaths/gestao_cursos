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

import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

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
            this.patchCursoUseCase.execute(company.getId(), null);
        }).isInstanceOf(CursoNotFoundException.class);
    }
}
