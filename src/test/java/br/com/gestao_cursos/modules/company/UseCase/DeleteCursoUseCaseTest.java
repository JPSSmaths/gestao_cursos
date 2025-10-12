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
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class DeleteCursoUseCaseTest {
    @InjectMocks
    private DeleteCursoUseCase deleteCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Should not be able delete a non-existent course")
    public void should_not_be_able_delete_a_non_existent_course() {
        CursoEntity course = CursoEntity.builder()
                .id(UUID.randomUUID())
                .build();

        when(this.cursoRepository.findById(course.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            this.deleteCursoUseCase.execute(course.getId());
        }).isInstanceOf(CursoNotFoundException.class);
    }
}
