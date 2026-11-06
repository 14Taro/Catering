package ar.edu.unahur.obj2.catering;

// Justificación: Error de instanciación por datos inválidos de negocio, no es un fallo recuperable de infraestructura.
public class PrecioNegativoException extends RuntimeException {
    public PrecioNegativoException(String mensaje) {
        super(mensaje);
    }
}