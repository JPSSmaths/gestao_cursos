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
import br.com.gestao_cursos.exceptions.CursoAlreadyExistsException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.CreateCursoDTO;

@ExtendWith(MockitoExtension.class)
public class CreateCursoUseCaseTest {
    @InjectMocks
    private CreateCursoUseCase createCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private CompanyRepository companyRepository;

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

    @Test
    @DisplayName("Should not able to create a course if a company does not exist")
    public void should_not_be_able_to_create_a_course_if_a_company_does_not_exist() {
        CreateCursoDTO createCursoDTO = CreateCursoDTO.builder()
                .name("Java")
                .build();

        CompanyEntity companyEntity = CompanyEntity.builder()
                .id(UUID.randomUUID())
                .build();

        when(this.companyRepository.findById(companyEntity.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> this.createCursoUseCase.execute(createCursoDTO, companyEntity.getId()))
                .isInstanceOf(CompanyNotFoundException.class);
    }


}
