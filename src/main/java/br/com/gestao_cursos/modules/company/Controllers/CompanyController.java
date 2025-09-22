package br.com.gestao_cursos.modules.company.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.UseCase.CreateCompanyUseCase;
import br.com.gestao_cursos.modules.company.dto.CreateCompanyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
@Tag(name = "Company Controller", description = "Methods responsables for company HTTP requests")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createComapnyUseCase;

    @Operation(summary = "Company creation", description = "This method is responsable for company creation")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = CompanyEntity.class))
        }),
        @ApiResponse(responseCode = "400", description = "Company not created")
    })
    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCompanyDTO createCompanyDTO){
        try {
            var result = this.createComapnyUseCase.create(createCompanyDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
