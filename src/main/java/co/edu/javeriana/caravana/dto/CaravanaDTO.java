package co.edu.javeriana.caravana.dto;

public class CaravanaDTO {

    private Long id;
    private String nombre;
    private Float velocidad;
    private Float capacidadMaximaCarga;
    private Float dinero;
    private Integer puntosVida;
    private Boolean tieneGuardias;
    private Float tiempoTranscurrido;
    private CiudadDTO ciudad;

    public CaravanaDTO() {
    }

    public CaravanaDTO(Long id, String nombre, Float velocidad, Float capacidadMaximaCarga, Float dinero, Integer puntosVida, Boolean tieneGuardias, Float tiempoTranscurrido, CiudadDTO ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.capacidadMaximaCarga = capacidadMaximaCarga;
        this.dinero = dinero;
        this.puntosVida = puntosVida;
        this.tieneGuardias = tieneGuardias;
        this.tiempoTranscurrido = tiempoTranscurrido;
        this.ciudad = ciudad;
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

    public Float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Float velocidad) {
        this.velocidad = velocidad;
    }

    public Float getCapacidadMaximaCarga() {
        return capacidadMaximaCarga;
    }

    public void setCapacidadMaximaCarga(Float capacidadMaximaCarga) {
        this.capacidadMaximaCarga = capacidadMaximaCarga;
    }

    public Float getDinero() {
        return dinero;
    }

    public void setDinero(Float dinero) {
        this.dinero = dinero;
    }

    public Integer getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(Integer puntosVida) {
        this.puntosVida = puntosVida;
    }

    public Boolean getTieneGuardias() {
        return tieneGuardias;
    }

    public void setTieneGuardias(Boolean tieneGuardias) {
        this.tieneGuardias = tieneGuardias;
    }

    public Float getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(Float tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public CiudadDTO getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }
}
