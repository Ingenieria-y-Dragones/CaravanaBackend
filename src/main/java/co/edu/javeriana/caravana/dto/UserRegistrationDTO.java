package co.edu.javeriana.caravana.dto;

import co.edu.javeriana.caravana.model.TipoJugador;

public class UserRegistrationDTO {
    private String nombre;
    private String correo;
    private String contrasenia;
    private TipoJugador tipo;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String nombre, String correo, String contrasenia, TipoJugador tipo) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoJugador getTipo() {
        return tipo;
    }

    public void setTipo(TipoJugador tipo) {
        this.tipo = tipo;
    }
}
