package co.edu.javeriana.caravana.dto;

import co.edu.javeriana.caravana.model.TipoServicio;

public class ServicioDTO {

    private Long id;
    private TipoServicio tipo;

    public ServicioDTO() {
    }

    public ServicioDTO(Long id, TipoServicio tipo) {
        this.id = id;
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
}
