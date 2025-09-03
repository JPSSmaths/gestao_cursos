package br.com.gestao_cursos.modules.company.curso.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestao_cursos.modules.company.curso.Entity.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, UUID>{
    Optional<CursoEntity> findByName(String name);
}