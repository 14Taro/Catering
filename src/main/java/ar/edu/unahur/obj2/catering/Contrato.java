package ar.edu.unahur.obj2.catering;

public class Contrato {
    private String nombreCliente;
    private Double importeFijado;
    private String descripcionFijada;

    public Contrato(String nombreCliente, Propuesta propuestaContratada) {
        this.nombreCliente = nombreCliente;
        this.importeFijado = propuestaContratada.getPrecio();
        this.descripcionFijada = propuestaContratada.getDescripcion();
    }

    public Double getImporteFijado() {
        return this.importeFijado;
    }

    public String getDescripcionFijada() {
        return this.descripcionFijada;
    }
}