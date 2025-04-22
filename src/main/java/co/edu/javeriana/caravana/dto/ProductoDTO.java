package co.edu.javeriana.caravana.dto;

import co.edu.javeriana.caravana.model.TipoProducto;

public class ProductoDTO {

    private Long id;
    private TipoProducto tipo;
    private String nombre;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, TipoProducto tipo) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

