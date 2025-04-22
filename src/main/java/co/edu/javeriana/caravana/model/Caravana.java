package co.edu.javeriana.caravana.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Caravana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Float velocidad;
    private Float capacidadMaximaCarga;
    private Float dinero;
    private Integer puntosVida;
    private Boolean tieneGuardias;
    private Float tiempoTranscurrido;

    @OneToMany(mappedBy = "caravana")
    private List<Jugador> jugadores = new ArrayList<Jugador>();

    @OneToMany(mappedBy = "caravana")
    private List<InventarioCaravana> productos = new ArrayList<InventarioCaravana>();

    @OneToMany(mappedBy = "caravana")
    private List<CompraServicio> serviciosComprados = new ArrayList<CompraServicio>();

    @ManyToOne
    private Ciudad ciudad;

    public Caravana() {
    }

    public Caravana(String nombre, Float velocidad, Float capacidadMaximaCarga, Float dinero, Integer puntosVida, Boolean tieneGuardias, Float tiempoTranscurrido) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.capacidadMaximaCarga = capacidadMaximaCarga;
        this.dinero = dinero;
        this.puntosVida = puntosVida;
        this.tieneGuardias = tieneGuardias;
        this.tiempoTranscurrido = tiempoTranscurrido;
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

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<InventarioCaravana> getProductos() {
        return productos;
    }

    public void setProductos(List<InventarioCaravana> productos) {
        this.productos = productos;
    }

    public List<CompraServicio> getServiciosComprados() {
        return serviciosComprados;
    }

    public void setServiciosComprados(List<CompraServicio> serviciosComprados) {
        this.serviciosComprados = serviciosComprados;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}