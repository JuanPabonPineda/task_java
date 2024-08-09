package prueba_tecnica.usuarios.dataprovider.jpa.entities;

import jakarta.persistence.*;
import lombok.*;
import prueba_tecnica.usuarios.configurations.JacocoAnnotationGenerated;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "USERS")
@JacocoAnnotationGenerated
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_user", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "fk_document_type", nullable = false)
    private Integer documentTypeId;

    @ManyToOne
    @JoinColumn(name = "fk_document_type", insertable = false, updatable = false)
    private DocumentTypeEntity documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "fk_rol")
    private Integer rolId;

    @ManyToOne
    @JoinColumn(name = "fk_rol", insertable = false, updatable = false)
    private RolesEntity rol;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "fk_status")
    private Integer statusId;

    @ManyToOne
    @JoinColumn(name = "fk_status", insertable = false, updatable = false)
    private StatusEntity status;
}
