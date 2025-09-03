package br.com.gestao_cursos.modules.company.curso.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;

@Service
public class GetCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public void execute(){
        
    }
}
