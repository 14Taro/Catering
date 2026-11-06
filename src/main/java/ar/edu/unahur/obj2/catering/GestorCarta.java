package ar.edu.unahur.obj2.catering;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GestorCarta {
    private Stack<Modificacion> historial;

    public GestorCarta() {
        this.historial = new Stack<>();
    }

    public void aplicar(Modificacion modificacion) {
        modificacion.aplicar();
        this.historial.push(modificacion);
    }

    public void revertir() {
        if (this.historial.isEmpty()) {
            throw new NadaParaRevertirException("El historial está vacío, no hay modificaciones para revertir.");
        }
        Modificacion ultimaModificacion = this.historial.pop();
        ultimaModificacion.revertir();
    }

    public void aplicarLote(List<Modificacion> lote) {
        List<Modificacion> aplicadasEnLote = new ArrayList<>();
        try {
            for (Modificacion modificacion : lote) {
                modificacion.aplicar();
                aplicadasEnLote.add(modificacion);
            }
            // Si todo sale bien, volcamos las aplicadas al historial en el orden correcto
            for (Modificacion modificacion : aplicadasEnLote) {
                this.historial.push(modificacion);
            }
        } catch (RuntimeException excepcion) {
            // Revertir en orden inverso las que sí llegaron a aplicarse en este lote
            for (int i = aplicadasEnLote.size() - 1; i >= 0; i--) {
                aplicadasEnLote.get(i).revertir();
            }
            throw excepcion; // Propagar la excepción hacia arriba
        }
    }
}