package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    @Query("SELECT r FROM Ruta r WHERE r.ciudadOrigen.id = (SELECT c.ciudad.id FROM Caravana c WHERE c.id = :caravanaId)")
    List<Ruta> findRutasByCiudadOrigenDeCaravana(@Param("caravanaId") Long caravanaId);
}
