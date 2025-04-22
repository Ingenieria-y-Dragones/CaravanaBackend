package co.edu.javeriana.caravana.model;

import jakarta.persistence.*;

@Entity
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Float distancia;
    private Float danio;
    private TipoPeligro peligro;

    @ManyToOne
    private Ciudad ciudadOrigen;

    @ManyToOne
    private Ciudad ciudadDestino;

    public Ruta() {
    }

    public Ruta(String nombre, Float distancia, Float danio, TipoPeligro peligro) {
        this.nombre = nombre;
        this.distancia = distancia;
        this.danio = danio;
        this.peligro = peligro;
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

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }
}