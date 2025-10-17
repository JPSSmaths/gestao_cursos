package br.com.gestao_cursos.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthCompanyRequestDTO {
    @Schema(example="accenture_company", requiredMode=RequiredMode.REQUIRED)
    private String username;
    @Schema(example="accenture_123", requiredMode=RequiredMode.REQUIRED)
    private String password;
}