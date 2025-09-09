package br.com.gestao_cursos.modules.company.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.UseCase.CreateComapnyUseCase;
import br.com.gestao_cursos.modules.company.UseCase.GetCompanyUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateComapnyUseCase createComapnyUseCase;

    @Autowired
    private GetCompanyUseCase getCompanyUseCase;
    
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid CompanyEntity companyEntity){
        try {
            var result = this.createComapnyUseCase.create(companyEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole(ROLE_COMPNY)")
    public ResponseEntity<Object> get(HttpServletRequest request){
        try {
            var result = this.getCompanyUseCase.get(request);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
