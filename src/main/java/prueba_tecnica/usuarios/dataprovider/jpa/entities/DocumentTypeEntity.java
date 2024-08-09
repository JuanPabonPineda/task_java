package prueba_tecnica.usuarios.dataprovider.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prueba_tecnica.usuarios.configurations.JacocoAnnotationGenerated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DOCUMENT_TYPE")
@Getter
@JacocoAnnotationGenerated
public class DocumentTypeEntity {

    @Id
    @Column(name = "pk_document_type", nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;
}
