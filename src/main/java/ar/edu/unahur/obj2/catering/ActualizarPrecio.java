package ar.edu.unahur.obj2.catering;

public class ActualizarPrecio implements Modificacion {
    private Servicio servicio;
    private Double nuevoPrecio;
    private Double precioAnterior; // Guarda el estado para poder revertir

    public ActualizarPrecio(Servicio servicio, Double nuevoPrecio) {
        this.servicio = servicio;
        this.nuevoPrecio = nuevoPrecio;
    }

    @Override
    public void aplicar() {
        this.precioAnterior = this.servicio.getPrecio();
        this.servicio.setPrecio(this.nuevoPrecio);
    }

    @Override
    public void revertir() {
        if (this.precioAnterior != null) {
            this.servicio.setPrecio(this.precioAnterior);
        }
    }
}