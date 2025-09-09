package br.com.gestao_cursos.modules.company.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class GetCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public void get(HttpServletRequest request){

    }
}
