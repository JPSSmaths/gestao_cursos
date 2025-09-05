package br.com.gestao_cursos.modules.company.curso.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@Service
public class CreateCursoUseCase {

    @Autowired
    private CursoRepository cursoRepository;
    
    public CursoEntity execute(CursoEntity cursoEntity){
        this.cursoRepository.findByName(cursoEntity.getName())
        .ifPresent(user -> {
            throw new UserFoundException("Usuário já existe");
        });

        return this.cursoRepository.save(cursoEntity);
    }
}