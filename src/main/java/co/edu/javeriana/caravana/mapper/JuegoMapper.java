package co.edu.javeriana.caravana.mapper;

import co.edu.javeriana.caravana.dto.JuegoDTO;
import co.edu.javeriana.caravana.model.Juego;

public class JuegoMapper {

    public static JuegoDTO toDTO(Juego juego) {
        JuegoDTO juegoDTO = new JuegoDTO();
        juegoDTO.setId(juego.getId());
        juegoDTO.setTiempoLimite(juego.getTiempoLimite());
        juegoDTO.setGananciasMinimas(juego.getGananciasMinimas());
        return juegoDTO;
    }

    public static Juego toEntity(JuegoDTO juegoDTO) {
        Juego juego = new Juego();
        juego.setId(juegoDTO.getId());
        juego.setTiempoLimite(juegoDTO.getTiempoLimite());
        juego.setGananciasMinimas(juegoDTO.getGananciasMinimas());
        return juego;
    }
}
