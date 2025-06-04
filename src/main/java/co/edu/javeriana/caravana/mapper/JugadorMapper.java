package co.edu.javeriana.caravana.mapper;

import co.edu.javeriana.caravana.dto.JugadorDTO;
import co.edu.javeriana.caravana.model.Jugador;

public class JugadorMapper {

    public static JugadorDTO toDTO(Jugador jugador) {
        JugadorDTO jugadorDTO = new JugadorDTO();
        jugadorDTO.setId(jugador.getId());
        jugadorDTO.setNombre(jugador.getNombre());
        jugadorDTO.setTipo(jugador.getTipo());
        jugadorDTO.setEmail(jugador.getEmail());
        return jugadorDTO;
    }

    public static Jugador toEntity(JugadorDTO jugadorDTO) {
        Jugador jugador = new Jugador();
        jugador.setId(jugadorDTO.getId());
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setTipo(jugadorDTO.getTipo());
        jugador.setEmail(jugadorDTO.getEmail());
        return jugador;
    }
}
