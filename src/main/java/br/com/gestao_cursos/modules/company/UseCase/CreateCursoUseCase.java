package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CursoAlredyExistsException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.CreateCursoDTO;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class CreateCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public CursoEntity execute(CreateCursoDTO createCursoDTO, HttpServletRequest request) {
        this.cursoRepository.findByName(createCursoDTO.getName())
                .ifPresent(user -> {
                    throw new CursoAlredyExistsException();
                });

        var cursoEntity = CursoEntity.builder()
                .name(createCursoDTO.getName())
                .category(createCursoDTO.getCategory())
                .active(createCursoDTO.getActive())
                .build();

        var company_id = UUID.fromString(request.getAttribute("company_id").toString());
        CompanyEntity company = this.companyRepository.findById(company_id).orElseThrow();
        cursoEntity.setCompanyId(company_id);
        cursoEntity.setCompany(company);

        return this.cursoRepository.save(cursoEntity);
    }
}