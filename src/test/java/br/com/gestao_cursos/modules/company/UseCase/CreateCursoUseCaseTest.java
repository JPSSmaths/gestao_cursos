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

import br.com.gestao_cursos.exceptions.CursoAlreadyExistsException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.CreateCursoDTO;

@ExtendWith(MockitoExtension.class)
public class CreateCursoUseCaseTest {
    @InjectMocks
    private CreateCursoUseCase createCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    @Test
    @DisplayName("Should not able to create a new course when curso already exists")
    public void should_not_able_to_create_a_new_course_when_curso_already_exists() {
        CreateCursoDTO createCursoDTO = CreateCursoDTO.builder()
                .name("Java")
                .build();

        CursoEntity cursoEntity = CursoEntity.builder()
                .name(createCursoDTO.getName())
                .build();

        CompanyEntity companyEntity = CompanyEntity.builder()
                .id(java.util.UUID.randomUUID())
                .build();

        when(cursoRepository.findByName(createCursoDTO.getName())).thenReturn(Optional.of(cursoEntity));

        assertThatThrownBy(() -> this.createCursoUseCase.execute(createCursoDTO, companyEntity.getId()))
                .isInstanceOf(CursoAlreadyExistsException.class);
    }
}
