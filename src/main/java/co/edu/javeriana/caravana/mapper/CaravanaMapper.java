package co.edu.javeriana.caravana.mapper;

import co.edu.javeriana.caravana.dto.CaravanaDTO;
import co.edu.javeriana.caravana.model.Caravana;

public class CaravanaMapper {

    public static CaravanaDTO toDTO(Caravana caravana) {
        CaravanaDTO caravanaDTO = new CaravanaDTO();
        caravanaDTO.setId(caravana.getId());
        caravanaDTO.setNombre(caravana.getNombre());
        caravanaDTO.setVelocidad(caravana.getVelocidad());
        caravanaDTO.setCapacidadMaximaCarga(caravana.getCapacidadMaximaCarga());
        caravanaDTO.setDinero(caravana.getDinero());
        caravanaDTO.setPuntosVida(caravana.getPuntosVida());
        caravanaDTO.setTieneGuardias(caravana.getTieneGuardias());
        caravanaDTO.setTiempoTranscurrido(caravana.getTiempoTranscurrido());
        caravanaDTO.setCiudad(CiudadMapper.toDTO(caravana.getCiudad()));
        return caravanaDTO;
    }

    public static Caravana toEntity(CaravanaDTO caravanaDTO) {
        Caravana caravana = new Caravana();
        caravana.setId(caravanaDTO.getId());
        caravana.setNombre(caravanaDTO.getNombre());
        caravana.setVelocidad(caravanaDTO.getVelocidad());
        caravana.setCapacidadMaximaCarga(caravanaDTO.getCapacidadMaximaCarga());
        caravana.setDinero(caravanaDTO.getDinero());
        caravana.setPuntosVida(caravanaDTO.getPuntosVida());
        caravana.setTieneGuardias(caravanaDTO.getTieneGuardias());
        caravana.setTiempoTranscurrido(caravanaDTO.getTiempoTranscurrido());
        caravana.setCiudad(CiudadMapper.toEntity(caravanaDTO.getCiudad()));
        return caravana;
    }
}