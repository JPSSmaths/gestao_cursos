package br.com.gestao_cursos.modules.company.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gestao_cursos.exceptions.UserFoundException;
import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.Repository.CompanyRepository;
import br.com.gestao_cursos.modules.company.dto.CreateCompanyDTO;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity create(CreateCompanyDTO createCompanyDTO){
        this.companyRepository.findByUsernameOrEmail(createCompanyDTO.getUsername(), createCompanyDTO.getEmail())
            .ifPresent(user -> {
                throw new UserFoundException("Usuário já existe");
            });
        
        var companyEntity = CompanyEntity.builder()
            .username(createCompanyDTO.getUsername())
            .password(createCompanyDTO.getPassword())
            .email(createCompanyDTO.getEmail())
            .description(createCompanyDTO.getDescription())
            .build();

        var passwordEncoded = this.passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(passwordEncoded);
        
        return this.companyRepository.save(companyEntity);
    }
}
