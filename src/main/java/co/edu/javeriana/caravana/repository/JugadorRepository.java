package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    Optional<Jugador> findByEmail(String email);

    @Query("SELECT j.caravana FROM Jugador j WHERE j.email = :email")
    Optional<Caravana> findCaravanaByEmail(@Param("email") String email);
}
