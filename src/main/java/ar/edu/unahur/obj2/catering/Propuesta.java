package ar.edu.unahur.obj2.catering;

public interface Propuesta {
    Double getPrecio();
    String getDescripcion();
    Integer getCantidadDeServicios();
    Boolean incluye(String nombreServicio);
}