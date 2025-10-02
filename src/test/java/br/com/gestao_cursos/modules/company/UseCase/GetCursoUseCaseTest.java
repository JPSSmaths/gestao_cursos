package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.utils.JWTCompanyProviderTest;
import br.com.gestao_cursos.modules.company.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
public class GetCursoUseCaseTest {
    
    @InjectMocks
    private GetCursoUseCase getCursoUseCase;

    @Mock
    private CursoRepository cursoRepository;

    public void should_be_able_extract_a_company_id_from_token(){
        String token = TestUtils.generateToken(UUID.randomUUID(), "COMPANY_123");

        UUID companyId = UUID.fromString(JWTCompanyProviderTest.decodeToken(token).getSubject());
    }
}
