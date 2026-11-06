package ar.edu.unahur.obj2.catering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Etapa1Test {

    private Servicio mesaDeEntradas;
    private Servicio parrillada;
    private Servicio mesaDulce;
    private Servicio barraTragos;
    private Servicio dj;
    private Servicio brindis;
    
    private Menu menuParrillero;
    private Menu packFiesta;
    private Menu eventoCompleto;

    @BeforeEach
    void setUp() {
        // Catálogo base
        mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        parrillada = new Servicio("Parrillada completa", 240000.0);
        mesaDulce = new Servicio("Mesa dulce", 60000.0);
        barraTragos = new Servicio("Barra de tragos", 150000.0);
        dj = new Servicio("DJ y sonido", 120000.0);
        brindis = new Servicio("Brindis de bienvenida", 50000.0);

        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregarPropuesta(mesaDeEntradas);
        menuParrillero.agregarPropuesta(parrillada);
        menuParrillero.agregarPropuesta(mesaDulce);

        packFiesta = new Menu("Pack Fiesta", 5.0);
        packFiesta.agregarPropuesta(barraTragos);
        packFiesta.agregarPropuesta(dj);

        eventoCompleto = new Menu("Evento Completo", 20.0);
        eventoCompleto.agregarPropuesta(menuParrillero);
        eventoCompleto.agregarPropuesta(packFiesta);
        eventoCompleto.agregarPropuesta(brindis);
    }

    @Test
    void dadoUnServicioConPrecioNegativo_alCrearlo_lanzaPrecioNegativoException() {
        assertThrows(PrecioNegativoException.class, () -> {
            new Servicio("Servicio Inválido", -10.0);
        });
    }

    @Test
    void dadoUnMenuConDescuentoMayorACien_alCrearlo_lanzaDescuentoInvalidoException() {
        assertThrows(DescuentoInvalidoException.class, () -> {
            new Menu("Menú Inválido", 150.0);
        });
    }

    @Test
    void dadoUnMenuConServicios_alCalcularPrecio_aplicaElDescuentoCorrespondiente() {
        // Given
        // When
        Double precioCalculado = menuParrillero.getPrecio();
        // Then (380.000 - 10% = 342.000)
        assertEquals(342000.0, precioCalculado);
    }

    @Test
    void dadoUnMenuQueContieneOtrosMenus_alCalcularPrecio_aplicaDescuentosEnCascada() {
        // Given
        // When
        Double precioCalculado = eventoCompleto.getPrecio();
        // Then (342.000 + 256.500 + 50.000) - 20% = 518.800
        assertEquals(518800.0, precioCalculado);
    }

    @Test
    void dadoUnMenuQueContieneOtrosMenus_alContarServicios_sumaLosDeTodosLosNiveles() {
        // Given
        // When
        Integer cantidadServicios = eventoCompleto.getCantidadDeServicios();
        // Then (3 de Parrillero + 2 de Fiesta + 1 Brindis = 6)
        assertEquals(6, cantidadServicios);
    }

    @Test
    void dadoUnMenuQueContieneOtrosMenus_alConsultarSiIncluyeUnServicio_buscaEnTodosLosNiveles() {
        // Given
        // When
        Boolean incluyeDJ = eventoCompleto.incluye("DJ y sonido");
        Boolean incluyeFotografo = eventoCompleto.incluye("Fotógrafo");

        // Then
        assertTrue(incluyeDJ);
        assertFalse(incluyeFotografo);
    }
}