package br.com.gestao_cursos.modules.company.UseCase;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class PutCursoUseCaseTest {
    @InjectMocks
    private PutCursoUseCase putCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    
}
