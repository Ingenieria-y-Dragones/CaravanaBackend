package co.edu.javeriana.caravana.dto;

public class JuegoDTO {

    private Long id;
    private Float tiempoLimite;
    private Float gananciasMinimas;

    public JuegoDTO() {
    }

    public JuegoDTO(Long id, Float tiempoLimite, Float gananciasMinimas) {
        this.id = id;
        this.tiempoLimite = tiempoLimite;
        this.gananciasMinimas = gananciasMinimas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(Float tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    public Float getGananciasMinimas() {
        return gananciasMinimas;
    }

    public void setGananciasMinimas(Float gananciasMinimas) {
        this.gananciasMinimas = gananciasMinimas;
    }
}
