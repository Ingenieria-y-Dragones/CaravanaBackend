package co.edu.javeriana.caravana.mapper;

import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.model.Ruta;

public class RutaMapper {

    public static RutaDTO toDTO(Ruta ruta) {
        RutaDTO rutaDTO = new RutaDTO();
        rutaDTO.setId(ruta.getId());
        rutaDTO.setNombre(ruta.getNombre());
        rutaDTO.setDistancia(ruta.getDistancia());
        rutaDTO.setDanio(ruta.getDanio());
        rutaDTO.setPeligro(ruta.getPeligro());
        return rutaDTO;
    }

    public static Ruta toEntity(RutaDTO rutaDTO) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDTO.getId());
        ruta.setNombre(rutaDTO.getNombre());
        ruta.setDistancia(rutaDTO.getDistancia());
        ruta.setDanio(rutaDTO.getDanio());
        ruta.setPeligro(rutaDTO.getPeligro());
        return ruta;
    }
}
