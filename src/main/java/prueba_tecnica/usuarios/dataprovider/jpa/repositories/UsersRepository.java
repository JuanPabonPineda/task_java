package prueba_tecnica.usuarios.dataprovider.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prueba_tecnica.usuarios.dataprovider.jpa.entities.UserEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity getById(Integer id);

    List<UserEntity> findAllByCreationDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("DELETE FROM UserEntity WHERE id = :userId")
    void delete(Integer userId);
}

