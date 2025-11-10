package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.PutCursoDTO;

@Service
public class PutCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public CursoEntity execute(UUID curso_id, PutCursoDTO putCursoDTO, UUID company_id) { 
        return this.cursoRepository.findById(curso_id).map(curso -> {
            if(!curso.getCompanyId().equals(company_id)){
                throw new CursoNotFoundException();
            }

            curso.setName(putCursoDTO.getName());
            curso.setCategory(putCursoDTO.getCategory());
            curso.setActive(putCursoDTO.getActive());

            return this.cursoRepository.save(curso);
        })
                .orElseThrow(() -> {
                    throw new CursoNotFoundException();
                });
    }
}
