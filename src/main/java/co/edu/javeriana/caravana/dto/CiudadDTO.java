package co.edu.javeriana.caravana.dto;

public class CiudadDTO {

    private Long id;
    private String nombre;
    private Float impuesto;

    public CiudadDTO() {
    }

    public CiudadDTO(Long id, String nombre, Float impuesto) {
        this.id = id;
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
}