package br.com.gestao_cursos.modules.company.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.UseCase.AuthCompanyUseCase;
import br.com.gestao_cursos.modules.company.dto.AuthCompanyRequestDTO;

@RestController
@RequestMapping("/company/auth")
public class AuthCompanyController {
    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    private ResponseEntity<Object> auth(@RequestBody AuthCompanyRequestDTO authCompanyRequestDTO){
        try {
            var result = this.authCompanyUseCase.auth(authCompanyRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}