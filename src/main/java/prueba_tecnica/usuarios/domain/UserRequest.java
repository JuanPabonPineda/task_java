package prueba_tecnica.usuarios.domain;

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
@JacocoAnnotationGenerated
public class UserRequest {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("type_document_id")
    private Integer documentTypeID;

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
