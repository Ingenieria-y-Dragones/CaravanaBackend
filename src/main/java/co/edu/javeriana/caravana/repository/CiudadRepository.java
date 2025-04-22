package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
