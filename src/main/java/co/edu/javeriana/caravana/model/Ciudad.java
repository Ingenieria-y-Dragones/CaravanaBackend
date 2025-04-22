package co.edu.javeriana.caravana.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Float impuesto;

    @OneToMany(mappedBy = "ciudad")
    private List<Caravana> caravanas = new ArrayList<Caravana>();

    @OneToMany(mappedBy = "ciudadOrigen")
    private List<Ruta> rutasEntrantes = new ArrayList<Ruta>();

    @OneToMany(mappedBy = "ciudadDestino")
    private List<Ruta> rutasSalientes = new ArrayList<Ruta>();

    @OneToMany(mappedBy = "ciudad")
    private List<InventarioCiudad> productos = new ArrayList<InventarioCiudad>();

    @OneToMany(mappedBy = "ciudad")
    private List<ServicioOfrecido> servicios = new ArrayList<ServicioOfrecido>();

    @OneToMany(mappedBy = "ciudad")
    private List<CompraServicio> serviciosComprados = new ArrayList<CompraServicio>();

    public Ciudad() {
    }

    public Ciudad(String nombre, Float impuesto) {
        this.nombre = nombre;
        this.impuesto = impuesto;
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

    public Float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Float impuesto) {
        this.impuesto = impuesto;
    }

    public List<Caravana> getCaravanas() {
        return caravanas;
    }

    public void setCaravanas(List<Caravana> caravanas) {
        this.caravanas = caravanas;
    }

    public List<Ruta> getRutasEntrantes() {
        return rutasEntrantes;
    }

    public void setRutasEntrantes(List<Ruta> rutasEntrantes) {
        this.rutasEntrantes = rutasEntrantes;
    }

    public List<Ruta> getRutasSalientes() {
        return rutasSalientes;
    }

    public void setRutasSalientes(List<Ruta> rutasSalientes) {
        this.rutasSalientes = rutasSalientes;
    }

    public List<InventarioCiudad> getProductos() {
        return productos;
    }

    public void setProductos(List<InventarioCiudad> productos) {
        this.productos = productos;
    }

    public List<ServicioOfrecido> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioOfrecido> servicios) {
        this.servicios = servicios;
    }

    public List<CompraServicio> getServiciosComprados() {
        return serviciosComprados;
    }

    public void setServiciosComprados(List<CompraServicio> serviciosComprados) {
        this.serviciosComprados = serviciosComprados;
    }
}