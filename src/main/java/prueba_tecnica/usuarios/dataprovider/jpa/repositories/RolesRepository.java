package prueba_tecnica.usuarios.dataprovider.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.RolesEntity;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {

}

