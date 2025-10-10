package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
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

    @Test
    public void should_be_able_extract_a_company_id_from_token(){
        UUID esperado = UUID.randomUUID();
        String token = TestUtils.generateToken(esperado, "COMPANY_123");

        UUID companyId = UUID.fromString(JWTCompanyProviderTest.decodeToken(token).getSubject());

        assertNotNull(companyId);
        assertEquals(esperado, companyId);
    }

    
}
