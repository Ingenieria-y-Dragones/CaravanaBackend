package co.edu.javeriana.caravana.dto;

import co.edu.javeriana.caravana.model.TipoPeligro;

public class RutaDTO {

    private Long id;
    private String nombre;
    private Float distancia;
    private Float danio;
    private TipoPeligro peligro;
    private CiudadDTO ciudadOrigen;
    private CiudadDTO ciudadDestino;

    public RutaDTO() {
    }

    public RutaDTO(Long id, String nombre, Float distancia, Float danio, TipoPeligro peligro, CiudadDTO ciudadOrigen, CiudadDTO ciudadDestino) {
        this.id = id;
        this.nombre = nombre;
        this.distancia = distancia;
        this.danio = danio;
        this.peligro = peligro;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public Float getDanio() {
        return danio;
    }

    public void setDanio(Float danio) {
        this.danio = danio;
    }

    public TipoPeligro getPeligro() {
        return peligro;
    }

    public void setPeligro(TipoPeligro peligro) {
        this.peligro = peligro;
    }

    public CiudadDTO getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(CiudadDTO ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public CiudadDTO getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(CiudadDTO ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }
}