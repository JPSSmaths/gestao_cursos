package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.curso.Active;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.PatchCursoDTO;

@Service
public class PatchCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public CursoEntity execute(UUID cursoId, PatchCursoDTO patchCursoDTO, UUID company_id){
        return this.cursoRepository.findById(cursoId).map(curso -> {
            if(!curso.getCompanyId().equals(company_id)){
                throw new CursoNotFoundException();
            }

            curso.setActive(Active.valueOf(patchCursoDTO.active().toUpperCase()));
            return this.cursoRepository.save(curso);
        })
                .orElseThrow(() -> {
                    throw new CursoNotFoundException();
                });
    }
}
