package br.com.gestao_cursos.modules.company.UseCase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@Service
public class GetCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List execute(UUID companyId){
        return cursoRepository.findAllByCompanyId(companyId)
            .orElseThrow(() -> new CursoNotFoundException());
    }
}
