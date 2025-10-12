package br.com.gestao_cursos.modules.company.UseCase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class PutCursoUseCaseTest {
    @InjectMocks
    private PutCursoUseCase putCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Should not be possible to update a non-existent course")
    public void should_not_be_possible_to_update_a_non_existent_course() {
        when(this.cursoRepository.findById(null)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            this.putCursoUseCase.execute(null, null);
        }).isInstanceOf(CursoNotFoundException.class);
    }
}
