package co.edu.javeriana.caravana.model;

import jakarta.persistence.*;

@Entity
public class InventarioCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Ciudad ciudad;

    private Long existencias;
    private Float factorDemanda;
    private Float factorOferta;

    public InventarioCiudad() {
    }

    public InventarioCiudad(Long existencias, Float factorDemanda, Float factorOferta) {
        this.existencias = existencias;
        this.factorDemanda = factorDemanda;
        this.factorOferta = factorOferta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Long getExistencias() {
        return existencias;
    }

    public void setExistencias(Long existencias) {
        this.existencias = existencias;
    }

    public Float getFactorDemanda() {
        return factorDemanda;
    }

    public void setFactorDemanda(Float factorDemanda) {
        this.factorDemanda = factorDemanda;
    }

    public Float getFactorOferta() {
        return factorOferta;
    }

    public void setFactorOferta(Float factorOferta) {
        this.factorOferta = factorOferta;
    }
}