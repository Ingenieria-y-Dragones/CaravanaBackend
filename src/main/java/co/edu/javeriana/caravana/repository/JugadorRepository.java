package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    Optional<Jugador> findByEmail(String email);
}
