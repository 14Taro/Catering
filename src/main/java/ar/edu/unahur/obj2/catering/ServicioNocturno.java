package ar.edu.unahur.obj2.catering;

public class ServicioNocturno extends Adicional {
    public ServicioNocturno(Propuesta propuestaEnvuelta) {
        super(propuestaEnvuelta);
    }

    @Override
    public Double getPrecio() {
        Double precioBase = this.propuestaEnvuelta.getPrecio();
        return precioBase + (precioBase * 0.20);
    }

    @Override
    public String getDescripcion() {
        return this.propuestaEnvuelta.getDescripcion() + " + Servicio nocturno";
    }
}