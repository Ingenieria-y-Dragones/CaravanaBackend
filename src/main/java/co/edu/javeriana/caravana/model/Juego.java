package co.edu.javeriana.caravana.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float tiempoLimite;
    private Float gananciasMinimas;

    public Juego() {
    }

    public Juego(Float tiempoLimite, Float gananciasMinimas) {
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