package br.com.gestao_cursos.modules.company.Entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "company")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Schema(example = "accenture_company")
    @Pattern(regexp = "\\S+", message = "The [username] field cannot contain blank spaces")
    @Column(unique=true)
    private String username;

    @Schema(example = "accenture_123")
    @Length(min = 5, max = 100, message = "The [password] field have to be between 5 and 100 characteres")
    private String password;

    @Schema(example = "accenture@gmail.com")
    @Email(message = "The fieled [email] have to be valid")
    @Column(unique=true)
    private String email;

    @Schema(example = "technology consulting company")
    private String description;

    @CreationTimestamp
    private LocalDate created_at;

    @UpdateTimestamp
    private LocalDate updated_at;
}
