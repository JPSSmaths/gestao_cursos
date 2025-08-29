package br.com.gestao_cursos.modules.curso.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.modules.curso.CursoFoundException;
import br.com.gestao_cursos.modules.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.curso.Repository.CursoRepository;

@Service
public class CreateCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;
    
    public CursoEntity execute(CursoEntity cursoEntity){
        this.cursoRepository.findByName(cursoEntity.getName())
        .ifPresent(user -> {
            throw new CursoFoundException("Usuário já existe");
        });

        return this.cursoRepository.save(cursoEntity);
    }
}