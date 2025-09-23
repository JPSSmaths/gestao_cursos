package br.com.gestao_cursos.modules.company.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CompanyAlredyExistsException;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class GetCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public List execute(HttpServletRequest request){
        var companyId = UUID.fromString(request.getAttribute("company_id").toString());

        List cursos = this.cursoRepository.findAllByCompanyId(companyId)
            .orElseThrow(() -> {
                throw new CompanyAlredyExistsException("Company not found");
            });
        
        return cursos;
    }
}
