package br.com.gestao_cursos.modules.company.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.UseCase.AuthCompanyUseCase;
import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/company/auth")
@Tag(name = "Company authentication", description = "Token company creation")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @Operation(summary = "Token generation", description = "This function is responsable for generate company token")
    @PostMapping("/create")
    private ResponseEntity<Object> auth(@RequestBody AuthCompanyRequestDTO authCompanyRequestDTO){
        try {
            var result = this.authCompanyUseCase.auth(authCompanyRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}