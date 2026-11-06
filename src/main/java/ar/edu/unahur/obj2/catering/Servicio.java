package ar.edu.unahur.obj2.catering;

public class Servicio implements Propuesta {
    private String nombre;
    private Double precio;

    public Servicio(String nombre, Double precio) {
        if (precio < 0) {
            throw new PrecioNegativoException("El precio del servicio no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precio = precio;
    }

    public void setPrecio(Double nuevoPrecio) {
        if (nuevoPrecio < 0) {
            throw new PrecioNegativoException("El precio del servicio no puede ser negativo.");
        }
        this.precio = nuevoPrecio;
    }

    @Override
    public Double getPrecio() {
        return this.precio;
    }

    @Override
    public String getDescripcion() {
        return this.nombre;
    }

    @Override
    public Integer getCantidadDeServicios() {
        return 1;
    }

    @Override
    public Boolean incluye(String nombreServicio) {
        return this.nombre.equals(nombreServicio);
    }
}