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
import br.com.gestao_cursos.modules.company.curso.Active;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.PutCursoDTO;

@ExtendWith(MockitoExtension.class)
public class PutCursoUseCaseTest {
    @InjectMocks
    private PutCursoUseCase putCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Should not be possible to update a non-existent course")
    public void should_not_be_possible_to_update_a_non_existent_course() {
        var cursoId = UUID.randomUUID();
        when(this.cursoRepository.findById(cursoId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            this.putCursoUseCase.execute(cursoId, null);
        }).isInstanceOf(CursoNotFoundException.class);
    }

    @Test
    @DisplayName("Should be possible to update a existent course")
    public void should_be_possible_to_update_a_existent_course() {
        PutCursoDTO putCursoDTO = PutCursoDTO.builder()
                .name("COURSE_TEST_UPDATED")
                .category("CATEGORY_TEST_UPDATED")
                .active(Enum.valueOf(Active.class, "ACTIVE"))
                .build();
        
        CursoEntity course = CursoEntity.builder()
                .id(UUID.randomUUID())
                .name(putCursoDTO.getName())
                .category(putCursoDTO.getCategory())
                .active(putCursoDTO.getActive())
                .build();

        CursoEntity courseUpdated = course;
        courseUpdated.setName("TEST_NAME");
        courseUpdated.setCategory("CATEGORY_TEST");
        courseUpdated.setActive(Enum.valueOf(Active.class, "INACTIVE"));

        when(this.cursoRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(this.cursoRepository.save(course)).thenReturn(courseUpdated);

        var result = this.putCursoUseCase.execute(course.getId(), putCursoDTO);
        assertEquals(courseUpdated, result);
    }
}
