package br.com.gestao_cursos.modules.company.curso.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CreateCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    public CursoEntity execute(CursoEntity cursoEntity, HttpServletRequest request){
        this.cursoRepository.findByName(cursoEntity.getName())
        .ifPresent(user -> {
            throw new UserFoundException("Usuário já existe");
        });

        var company_id = UUID.fromString(request.getAttribute("company_id").toString());
        CompanyEntity company = this.companyRepository.findById(company_id).orElseThrow();
        cursoEntity.setCompany_id(company_id);
        cursoEntity.setCompany(company);

        return this.cursoRepository.save(cursoEntity);
    }
}