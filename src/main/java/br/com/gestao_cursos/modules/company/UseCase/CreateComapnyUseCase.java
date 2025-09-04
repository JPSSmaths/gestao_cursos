package br.com.gestao_cursos.modules.company.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;

@Service
public class CreateComapnyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity create(CompanyEntity companyEntity){
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent(user -> {
                throw new UserFoundException("Usuário já existe");
            });
        
        this.companyRepository.save(companyEntity);

        return companyEntity;
    }
}
