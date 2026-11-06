package ar.edu.unahur.obj2.catering;

// Justificación: Error de negocio al intentar operar sobre algo que no está en el menú.
public class PropuestaNoIncluidaException extends RuntimeException {
    public PropuestaNoIncluidaException(String mensaje) {
        super(mensaje);
    }
}