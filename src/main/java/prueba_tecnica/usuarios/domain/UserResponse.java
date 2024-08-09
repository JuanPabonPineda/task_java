package prueba_tecnica.usuarios.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import prueba_tecnica.usuarios.configurations.JacocoAnnotationGenerated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@JacocoAnnotationGenerated
public class UserResponse {

    @JsonIgnore
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("document_type")
    private Integer documentType;

    @JsonProperty("document_number")
    private String documentNumber;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("rol")
    private Integer rol;

    @JsonProperty("status")
    private Integer status;
}
