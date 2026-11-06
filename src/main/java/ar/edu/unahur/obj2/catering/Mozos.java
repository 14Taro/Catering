package ar.edu.unahur.obj2.catering;

public class Mozos extends Adicional {
    public Mozos(Propuesta propuestaEnvuelta) {
        super(propuestaEnvuelta);
    }

    @Override
    public Double getPrecio() {
        return this.propuestaEnvuelta.getPrecio() + 18000.0;
    }

    @Override
    public String getDescripcion() {
        return this.propuestaEnvuelta.getDescripcion() + " + Mozos adicionales";
    }
}