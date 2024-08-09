package prueba_tecnica.usuarios.domain;

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
public class TypeDO {
    private Integer id;
    private String description;
}
