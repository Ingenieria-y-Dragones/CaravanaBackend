package co.edu.javeriana.caravana.dto;

public class ServicioOfrecidoDTO {
    private Long id;
    private String nombreServicio;
    private Float precio;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreServicio() { return nombreServicio; }
    public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }

    public Float getPrecio() { return precio; }
    public void setPrecio(Float precio) { this.precio = precio; }
}
