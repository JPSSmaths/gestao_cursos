package br.com.gestao_cursos.modules.company.curso.dto;

import br.com.gestao_cursos.modules.company.curso.Active;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PutCursoDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String curso;
    @NotBlank
    private String category;
    @NotNull
    private Active active;
}