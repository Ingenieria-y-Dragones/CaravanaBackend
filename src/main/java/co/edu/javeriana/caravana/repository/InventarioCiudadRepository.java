package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.InventarioCiudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioCiudadRepository extends JpaRepository<InventarioCiudad, Long> {
    Optional<InventarioCiudad> findByCiudadIdAndProductoId(Long ciudadId, Long productoId);
}
