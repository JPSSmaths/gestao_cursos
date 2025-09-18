package br.com.gestao_cursos.modules.company.Controllers;

import java.awt.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.UseCase.CreateCursoUseCase;
import br.com.gestao_cursos.modules.company.UseCase.DeleteCursoUseCase;
import br.com.gestao_cursos.modules.company.UseCase.GetCursoUseCase;
import br.com.gestao_cursos.modules.company.UseCase.PatchCursoUseCase;
import br.com.gestao_cursos.modules.company.UseCase.PutCursoUseCase;
import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;
import br.com.gestao_cursos.modules.company.curso.dto.PatchCursoDTO;
import br.com.gestao_cursos.modules.company.curso.dto.PutCursoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/course")
@Tag(name = "Course Controller", description = "methods responsabçes for course HTTP requests")
public class CursoController {
    @Autowired
    private CreateCursoUseCase createCursoUseCase;

    @Autowired
    private GetCursoUseCase getCursoUseCase;

    @Autowired
    private PutCursoUseCase putCursoUseCase;

    @Autowired
    private DeleteCursoUseCase deleteCursoUseCase;

    @Autowired
    private PatchCursoUseCase patchCursoUseCase;

    @Operation(summary = "Create method", description = "Method reponsable for create a course")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = CursoEntity.class))
        }),
        @ApiResponse(responseCode = "400", description = "Company not cerated")
    })
    @SecurityRequirement(name = "jwt_auth")
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

    @Operation(summary = "Get method", description = "Method responsable for list all courses created")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = List.class))
        }),
        @ApiResponse(responseCode="400", description = "Unable to list courses")
    })
    @SecurityRequirement(name = "jwt_auth")
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

    @Operation(summary = "Put Method", description = "Method responsable for update all course atributs")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CursoEntity.class))
        }),
        @ApiResponse(responseCode = "400", description = "Unable update course")
    })
    @SecurityRequirement(name = "jwt_auth")
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

    @Operation(summary = "Delete method", description = "Method responsable for delete a course")
    @ApiResponses({
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "400", description = "Unable delete course")
    })
    @SecurityRequirement(name = "jwt_auth")
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

    @Operation(summary = "Patch method", description = "Method responsable for update the active atribut of course")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CursoEntity.class))
        }),
        @ApiResponse(responseCode = "400", description = "Unable update atribut active of course")
    })
    @SecurityRequirement(name = "jwt_auth")
    @PatchMapping("/patch/{curso_id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> patch(@PathVariable UUID curso_id, @RequestBody PatchCursoDTO patchCursoDTO){
        try {
            var result = this.patchCursoUseCase.execute(curso_id, patchCursoDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}