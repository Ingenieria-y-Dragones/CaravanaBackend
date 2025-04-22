package co.edu.javeriana.caravana.dto;

public class RutaDestinoDTO {

    private RutaDTO ruta;
    private CiudadDTO ciudadDestino;

    public RutaDestinoDTO() {
    }

    public RutaDestinoDTO(RutaDTO ruta, CiudadDTO ciudadDestino) {
        this.ruta = ruta;
        this.ciudadDestino = ciudadDestino;
    }

    public RutaDTO getRuta() {
        return ruta;
    }

    public void setRuta(RutaDTO ruta) {
        this.ruta = ruta;
    }

    public CiudadDTO getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(CiudadDTO ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }
}
