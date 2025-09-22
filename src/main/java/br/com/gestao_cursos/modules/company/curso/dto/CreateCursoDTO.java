package br.com.gestao_cursos.modules.company.curso.dto;

import br.com.gestao_cursos.modules.company.curso.Active;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCursoDTO {
    @Schema(example="Java course", requiredMode=RequiredMode.REQUIRED)
    private String name;
    @Schema(example="Programming course", requiredMode=RequiredMode.REQUIRED)
    private String category;
    @Schema(example="ACTIVE", requiredMode=RequiredMode.REQUIRED)
    private Active active; 
}
