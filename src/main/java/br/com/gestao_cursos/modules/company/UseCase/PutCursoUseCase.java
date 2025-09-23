package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.CompanyAlredyExistsException;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.Repository.CursoRepository;
import br.com.gestao_cursos.modules.company.curso.dto.PutCursoDTO;

@Service
public class PutCursoUseCase {
    @Autowired
    private CursoRepository cursoRepository;

    public CursoEntity execute(UUID curso_id, PutCursoDTO putCursoDTO) {
        return this.cursoRepository.findById(curso_id).map(curso -> {
            curso.setName(putCursoDTO.getName());
            curso.setCategory(putCursoDTO.getCategory());
            curso.setActive(putCursoDTO.getActive());

            return this.cursoRepository.save(curso);
        })
                .orElseThrow(() -> {
                    throw new CompanyAlredyExistsException("Course not found");
                });
    }
}
