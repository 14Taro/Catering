package ar.edu.unahur.obj2.catering;

public class VajillaPremium extends Adicional {
    public VajillaPremium(Propuesta propuestaEnvuelta) {
        super(propuestaEnvuelta);
    }

    @Override
    public Double getPrecio() {
        return this.propuestaEnvuelta.getPrecio() + 12000.0;
    }

    @Override
    public String getDescripcion() {
        return this.propuestaEnvuelta.getDescripcion() + " + Vajilla premium";
    }
}