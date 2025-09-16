package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.curso.Active;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.PatchCursoDTO;

@Service
public class PatchCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public CursoEntity execute(UUID cursoId, PatchCursoDTO patchCursoDTO){
        return this.cursoRepository.findById(cursoId).map(curso -> {
            if(patchCursoDTO.getName() != null){
                curso.setName(patchCursoDTO.getName());
            }

            if(patchCursoDTO.getCategory() != null){
                curso.setCategory(patchCursoDTO.getCategory());
            }

            Active status = patchCursoDTO.getActive();
            if(patchCursoDTO.getActive() != null && status == Active.ACTIVE){
                curso.setActive(patchCursoDTO.getActive());
            }else{
                throw new RuntimeException("The course isnt active");
            }

            return this.cursoRepository.save(curso);
        })
                .orElseThrow(() -> {
                    throw new UserFoundException("Course not found");
                });
    }
}
