package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CursoNotFoundException;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@Service
public class DeleteCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;
    
    public void execute(UUID curso_id){
        var curso = this.cursoRepository.findById(curso_id)
            .orElseThrow(() -> {
                throw new CursoNotFoundException();
            });
        
        this.cursoRepository.delete(curso);
    }
}
