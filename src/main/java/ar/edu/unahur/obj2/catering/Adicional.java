package ar.edu.unahur.obj2.catering;

public abstract class Adicional implements Propuesta {
    protected Propuesta propuestaEnvuelta;

    public Adicional(Propuesta propuestaEnvuelta) {
        this.propuestaEnvuelta = propuestaEnvuelta;
    }

    @Override
    public Integer getCantidadDeServicios() {
        return this.propuestaEnvuelta.getCantidadDeServicios();
    }

    @Override
    public Boolean incluye(String nombreServicio) {
        return this.propuestaEnvuelta.incluye(nombreServicio);
    }
}