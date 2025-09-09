package br.com.gestao_cursos.modules.company.UseCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class GetCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity get(HttpServletRequest request){
        UUID company_id = UUID.fromString(request.getAttribute("company_id").toString());

        var company = this.companyRepository.findById(company_id)
            .orElseThrow(() -> new UserFoundException("Company not found"));

        return company;
    }
}
