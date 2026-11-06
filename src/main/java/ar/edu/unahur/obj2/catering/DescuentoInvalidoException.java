package ar.edu.unahur.obj2.catering;

// Justificación: Violación de regla de dominio (porcentaje fuera de rango) al crear el objeto.
public class DescuentoInvalidoException extends RuntimeException {
    public DescuentoInvalidoException(String mensaje) {
        super(mensaje);
    }
}