package br.com.gestao_cursos.modules.company.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CompanyNotFoundException;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@Service
public class GetCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public List execute(UUID companyId){
        List cursos = this.cursoRepository.findAllByCompanyId(companyId)
            .orElseThrow(() -> {
                throw new CompanyNotFoundException();
            });
        
        return cursos;
    }
}
