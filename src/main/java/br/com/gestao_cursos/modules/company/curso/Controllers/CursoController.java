package br.com.gestao_cursos.modules.company.curso.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.UseCase.CreateCursoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CursoController {
    @Autowired
    private CreateCursoUseCase createCursoUseCase;

    @PostMapping("/create")
    @PreAuthorize("hasRole(ROLE_COMPANY)")
    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity cursoEntity, HttpServletRequest request){
        try {
            var result = this.createCursoUseCase.execute(cursoEntity, request);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}