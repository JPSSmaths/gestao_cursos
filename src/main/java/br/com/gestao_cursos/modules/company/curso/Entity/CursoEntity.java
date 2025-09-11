package br.com.gestao_cursos.modules.company.curso.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.gestao_cursos.modules.company.Entity.CompanyEntity;
import br.com.gestao_cursos.modules.company.curso.Active;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "course")
@Data
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String category;
    private Active active;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @Column(insertable = false, nullable = false)
    private UUID company_id;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}