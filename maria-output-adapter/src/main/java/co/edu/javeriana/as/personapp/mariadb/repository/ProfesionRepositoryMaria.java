package co.edu.javeriana.as.personapp.mariadb.repository;

import co.edu.javeriana.as.personapp.mariadb.entity.ProfesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionRepositoryMaria extends JpaRepository<ProfesionEntity, Integer> {
}
