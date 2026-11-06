package ar.edu.unahur.obj2.catering;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Propuesta {
    private String nombre;
    private Double porcentajeDescuento;
    private List<Propuesta> componentes;

    public Menu(String nombre, Double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new DescuentoInvalidoException("El descuento debe ser un porcentaje entre 0 y 100.");
        }
        this.nombre = nombre;
        this.porcentajeDescuento = porcentajeDescuento;
        this.componentes = new ArrayList<>();
    }

    public void agregarPropuesta(Propuesta propuesta) {
        this.componentes.add(propuesta);
    }

    public void removerPropuesta(Propuesta propuesta) {
        this.componentes.remove(propuesta);
    }

    public Boolean esComponenteDirecto(Propuesta propuesta) {
        return this.componentes.contains(propuesta);
    }

    @Override
    public Double getPrecio() {
        Double precioAcumulado = 0.0;
        for (Propuesta componente : this.componentes) {
            precioAcumulado += componente.getPrecio();
        }
        return precioAcumulado - (precioAcumulado * (this.porcentajeDescuento / 100.0));
    }

    @Override
    public String getDescripcion() {
        return this.nombre;
    }

    @Override
    public Integer getCantidadDeServicios() {
        Integer cantidadAcumulada = 0;
        for (Propuesta componente : this.componentes) {
            cantidadAcumulada += componente.getCantidadDeServicios();
        }
        return cantidadAcumulada;
    }

    @Override
    public Boolean incluye(String nombreServicio) {
        for (Propuesta componente : this.componentes) {
            if (componente.incluye(nombreServicio)) {
                return true;
            }
        }
        return false;
    }
}