package br.com.gestao_cursos.modules.company.curso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.curso.UseCase.CreateCursoUseCase;
import br.com.gestao_cursos.modules.curso.Entity.CursoEntity;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CreateCursoUseCase createCursoUseCase;

    public ResponseEntity<Object> create(@Validated @RequestBody CursoEntity cursoEntity){
        try {
            var result = this.createCursoUseCase.execute(cursoEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}