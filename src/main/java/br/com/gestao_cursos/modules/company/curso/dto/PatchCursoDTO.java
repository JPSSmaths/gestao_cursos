package br.com.gestao_cursos.modules.company.curso.dto;

import br.com.gestao_cursos.modules.company.curso.Active;
import lombok.Data;

@Data
public class PatchCursoDTO {
    private String name;
    private String category;
    private Active active;
}
