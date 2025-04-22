package co.edu.javeriana.caravana.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private String descripcion;

    private TipoProducto tipo;

    @OneToMany(mappedBy = "producto")
    private List<InventarioCiudad> productosCiudades = new ArrayList<InventarioCiudad>();

    @OneToMany(mappedBy = "producto")
    private List<InventarioCaravana> caravanas = new ArrayList<InventarioCaravana>();

    public Producto() {
    }

    public Producto(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public List<InventarioCiudad> getProductosCiudades() {
        return productosCiudades;
    }

    public void setProductosCiudades(List<InventarioCiudad> productosCiudades) {
        this.productosCiudades = productosCiudades;
    }

    public List<InventarioCaravana> getCaravanas() {
        return caravanas;
    }

    public void setCaravanas(List<InventarioCaravana> caravanas) {
        this.caravanas = caravanas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}