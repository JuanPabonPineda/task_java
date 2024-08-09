package prueba_tecnica.usuarios.dataprovider.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.StatusEntity;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    StatusEntity findByDescription(String description);
}

