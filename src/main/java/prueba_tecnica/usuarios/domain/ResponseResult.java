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
public class ResponseResult<T> {
    private boolean isError;
    private String message;
    private T data;
}
