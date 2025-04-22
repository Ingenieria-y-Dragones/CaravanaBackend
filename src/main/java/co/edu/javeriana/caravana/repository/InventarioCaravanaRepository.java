package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.InventarioCaravana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioCaravanaRepository extends JpaRepository<InventarioCaravana, Long> {
    Optional<InventarioCaravana> findByCaravanaIdAndProductoId(Long caravanaId, Long productoId);
}
