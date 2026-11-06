package ar.edu.unahur.obj2.catering;

public class AgregarPropuesta implements Modificacion {
    private Menu menu;
    private Propuesta propuesta;

    public AgregarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override
    public void aplicar() {
        this.menu.agregarPropuesta(this.propuesta);
    }

    @Override
    public void revertir() {
        this.menu.removerPropuesta(this.propuesta);
    }
}