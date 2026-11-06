package ar.edu.unahur.obj2.catering;

// Justificación: Violación de precondición al usar el gestor vacío.
public class NadaParaRevertirException extends RuntimeException {
    public NadaParaRevertirException(String mensaje) {
        super(mensaje);
    }
}