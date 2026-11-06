package ar.edu.unahur.obj2.catering;

// Justificación: Error de instanciación por datos inválidos de negocio.
public class PrecioNegativoException extends RuntimeException {
    public PrecioNegativoException(String mensaje) {
        super(mensaje);
    }
}