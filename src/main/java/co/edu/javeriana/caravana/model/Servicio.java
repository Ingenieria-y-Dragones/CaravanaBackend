package co.edu.javeriana.caravana.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String descripcion;

    private TipoServicio tipo;

    private float precio;

    @OneToMany(mappedBy = "servicio")
    List<ServicioOfrecido> ciudades = new ArrayList<ServicioOfrecido>();

    @OneToMany(mappedBy = "servicio")
    private List<CompraServicio> serviciosComprados = new ArrayList<CompraServicio>();

    public Servicio() {
    }

    public Servicio(TipoServicio tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoServicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoServicio tipo) {
        this.tipo = tipo;
    }

    public List<ServicioOfrecido> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<ServicioOfrecido> ciudades) {
        this.ciudades = ciudades;
    }

    public List<CompraServicio> getServiciosComprados() {
        return serviciosComprados;
    }

    public void setServiciosComprados(List<CompraServicio> serviciosComprados) {
        this.serviciosComprados = serviciosComprados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}