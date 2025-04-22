package co.edu.javeriana.caravana.dto;

import co.edu.javeriana.caravana.model.TipoJugador;

public class JugadorDTO {

    private Long id;
    private String nombre;
    private TipoJugador tipo;

    public JugadorDTO() {
    }

    public JugadorDTO(Long id, String nombre, TipoJugador tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
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

    public TipoJugador getTipo() {
        return tipo;
    }

    public void setTipo(TipoJugador tipo) {
        this.tipo = tipo;
    }
}