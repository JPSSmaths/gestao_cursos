package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CompanyNotFoundException;
import br.com.gestao_cursos.exceptions.CursoAlreadyExistsException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.CreateCursoDTO;

@Service
public class CreateCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public CursoEntity execute(CreateCursoDTO createCursoDTO, UUID company_id) {
        this.cursoRepository.findByName(createCursoDTO.getName())
                .ifPresent(user -> {
                    throw new CursoAlreadyExistsException();
                });

        var cursoEntity = CursoEntity.builder()
                .name(createCursoDTO.getName())
                .category(createCursoDTO.getCategory())
                .active(createCursoDTO.getActive())
                .build();

        CompanyEntity company = this.companyRepository.findById(company_id)
            .orElseThrow(() -> {
                throw new CompanyNotFoundException();
            });

        cursoEntity.setCompanyId(company_id);
        cursoEntity.setCompany(company);

        return this.cursoRepository.save(cursoEntity);
    }
}