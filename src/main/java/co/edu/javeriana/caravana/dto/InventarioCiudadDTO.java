package co.edu.javeriana.caravana.dto;

public class InventarioCiudadDTO {
    private Long id;
    private String nombreProducto;
    private Long existencias;
    private Float factorDemanda;
    private Float factorOferta;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public Long getExistencias() { return existencias; }
    public void setExistencias(Long existencias) { this.existencias = existencias; }

    public Float getFactorDemanda() { return factorDemanda; }
    public void setFactorDemanda(Float factorDemanda) { this.factorDemanda = factorDemanda; }

    public Float getFactorOferta() { return factorOferta; }
    public void setFactorOferta(Float factorOferta) { this.factorOferta = factorOferta; }
}
