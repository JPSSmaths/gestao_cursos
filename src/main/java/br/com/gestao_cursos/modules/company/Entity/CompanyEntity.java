package br.com.gestao_cursos.modules.company.Entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private String username;

    
    private String password;

    
    private String email;

    private LocalDate created_at;
    private LocalDate updated_at;
}
