package ar.edu.unahur.obj2.catering;

public class QuitarPropuesta implements Modificacion {
    private Menu menu;
    private Propuesta propuesta;

    public QuitarPropuesta(Menu menu, Propuesta propuesta) {
        this.menu = menu;
        this.propuesta = propuesta;
    }

    @Override
    public void aplicar() {
        if (!this.menu.esComponenteDirecto(this.propuesta)) {
            throw new PropuestaNoIncluidaException("No se puede quitar una propuesta que no es componente directo.");
        }
        this.menu.removerPropuesta(this.propuesta);
    }

    @Override
    public void revertir() {
        this.menu.agregarPropuesta(this.propuesta);
    }
}