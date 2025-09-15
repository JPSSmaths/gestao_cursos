package br.com.gestao_cursos.modules.company.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.UseCase.CreateCursoUseCase;
import br.com.gestao_cursos.modules.company.UseCase.DeleteCursoUseCase;
import br.com.gestao_cursos.modules.company.UseCase.GetCursoUseCase;
import br.com.gestao_cursos.modules.company.UseCase.PutCursoUseCase;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.dto.PutCursoDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/course")
public class CursoController {
    @Autowired
    private CreateCursoUseCase createCursoUseCase;

    @Autowired
    private GetCursoUseCase getCursoUseCase;

    @Autowired
    private PutCursoUseCase putCursoUseCase;

    @Autowired
    private DeleteCursoUseCase deleteCursoUseCase;

    @PostMapping("/create")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody CursoEntity cursoEntity, HttpServletRequest request){
        try {
            var result = this.createCursoUseCase.execute(cursoEntity, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> get(HttpServletRequest request){
        try {
            var result = this.getCursoUseCase.execute(request);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/put/{curso_id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> put(@PathVariable UUID curso_id, @Valid @RequestBody PutCursoDTO putCursoDTO){
        try {
            var result = this.putCursoUseCase.execute(curso_id, putCursoDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{curso_id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> delete(@PathVariable UUID curso_id){
        try {
            this.deleteCursoUseCase.execute(curso_id);
            return ResponseEntity.ok().body("Course deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}