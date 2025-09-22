package br.com.gestao_cursos.modules.company.dto;

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
public class CreateCompanyDTO {
    @Schema(example="accenture_company", requiredMode=RequiredMode.REQUIRED)
    private String username;
    @Schema(example="accenture_123", requiredMode=RequiredMode.REQUIRED)
    private String password;
    @Schema(example="accenture@gmail.com", requiredMode=RequiredMode.REQUIRED)
    private String email;
    @Schema(example = "technology consulting company", requiredMode=RequiredMode.REQUIRED)
    private String description;
}
