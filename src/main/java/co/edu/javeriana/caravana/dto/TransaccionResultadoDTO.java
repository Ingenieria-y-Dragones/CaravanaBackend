package co.edu.javeriana.caravana.dto;

public class TransaccionResultadoDTO {
    private boolean exito;
    private String mensaje;
    private float dineroRestante;
    private Integer saludActual;


    // Constructor sin args (requerido por JPA/Jackson)
    public TransaccionResultadoDTO() {}

    // Constructor con parámetros
    public TransaccionResultadoDTO(
            boolean exito,
            String mensaje,
            float dineroRestante,
            int saludActual
    ) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.dineroRestante = dineroRestante;
        this.saludActual = saludActual;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getSaludActual() {
        return saludActual;
    }

    public void setSaludActual(Integer saludActual) {
        this.saludActual = saludActual;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public float getDineroRestante() {
        return dineroRestante;
    }

    public void setDineroRestante(float dineroRestante) {
        this.dineroRestante = dineroRestante;
    }

}
