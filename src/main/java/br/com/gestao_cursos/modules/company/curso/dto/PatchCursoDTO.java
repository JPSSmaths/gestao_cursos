package br.com.gestao_cursos.modules.company.curso.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Builder;

@Builder
public record PatchCursoDTO(
        @Schema(example = "ACTIVE", requiredMode = RequiredMode.REQUIRED) String active) {

}
