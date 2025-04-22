package co.edu.javeriana.caravana.model;

import jakarta.persistence.*;

@Entity
public class InventarioCaravana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Caravana caravana;

    private Long existencias;

    public InventarioCaravana() {
    }

    public InventarioCaravana(Long existencias) {
        this.existencias = existencias;
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

    public Caravana getCaravana() {
        return caravana;
    }

    public void setCaravana(Caravana caravana) {
        this.caravana = caravana;
    }

    public Long getExistencias() {
        return existencias;
    }

    public void setExistencias(Long existencias) {
        this.existencias = existencias;
    }
}