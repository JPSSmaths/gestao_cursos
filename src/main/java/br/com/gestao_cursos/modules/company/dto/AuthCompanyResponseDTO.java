package br.com.gestao_cursos.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthCompanyResponseDTO {
    private String acess_token;
    private Long expires_in;
}
