package br.com.gestao_cursos.modules.company.curso.dto;

import br.com.gestao_cursos.modules.company.curso.Active;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
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
    @Schema(example = "Java course", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private String name;

    @Schema(example = "Programming course", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private String category;

    @Schema(example = "INACTIVE", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private Active active;
}