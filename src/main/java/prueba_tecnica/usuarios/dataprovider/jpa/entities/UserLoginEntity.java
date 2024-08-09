package prueba_tecnica.usuarios.dataprovider.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import prueba_tecnica.usuarios.configurations.JacocoAnnotationGenerated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "USER_LOGIN")
@JacocoAnnotationGenerated
public class UserLoginEntity {

    @Id
    @Column(name = "pk_user_login", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fk_user")
    private Integer userId;
}
