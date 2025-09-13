package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@Service
public class PutCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public CursoEntity execute(UUID curso_id, CursoEntity cursoEntity){
        var curso = this.cursoRepository.findById(curso_id)
            .orElseThrow(() -> {
                throw new UserFoundException("Course not found");
            });
        
        cursoEntity.setId(curso_id);
        cursoEntity.setCompany(curso.getCompany());
        cursoEntity.setCreated_at(curso.getCreated_at());
        return this.cursoRepository.save(cursoEntity);
    }
}
