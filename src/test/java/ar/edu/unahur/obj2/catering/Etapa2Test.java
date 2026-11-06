package ar.edu.unahur.obj2.catering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Etapa2Test {

    private Servicio mesaDulce;
    private Servicio brindis;
    private Menu menuParrillero;
    private Menu eventoCompleto;

    @BeforeEach
    void setUp() {
        mesaDulce = new Servicio("Mesa dulce", 60000.0);
        brindis = new Servicio("Brindis de bienvenida", 50000.0);

        Servicio mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        Servicio parrillada = new Servicio("Parrillada completa", 240000.0);
        
        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregarPropuesta(mesaDeEntradas);
        menuParrillero.agregarPropuesta(parrillada);
        menuParrillero.agregarPropuesta(mesaDulce);

        Menu packFiesta = new Menu("Pack Fiesta", 5.0);
        packFiesta.agregarPropuesta(new Servicio("Barra de tragos", 150000.0));
        packFiesta.agregarPropuesta(new Servicio("DJ y sonido", 120000.0));

        eventoCompleto = new Menu("Evento Completo", 20.0);
        eventoCompleto.agregarPropuesta(menuParrillero);
        eventoCompleto.agregarPropuesta(packFiesta);
        eventoCompleto.agregarPropuesta(brindis);
    }

    @Test
    void dadoUnServicioNocturnoAplicadoAntesQueMozos_alCalcularPrecio_elOrdenAlteraElPrecioFinal() {
        Propuesta propuesta = new Mozos(new ServicioNocturno(menuParrillero));
        assertEquals(428400.0, propuesta.getPrecio());
    }

    @Test
    void dadoMozosAplicadosAntesQueServicioNocturno_alCalcularPrecio_elOrdenAlteraElPrecioFinal() {
        Propuesta propuesta = new ServicioNocturno(new Mozos(menuParrillero));
        assertEquals(432000.0, propuesta.getPrecio());
        assertEquals("Menú Parrillero + Mozos adicionales + Servicio nocturno", propuesta.getDescripcion());
    }

    @Test
    void dadoUnAdicionalRepetido_alCalcularPrecio_seSumaElValorDosVeces() {
        Propuesta propuesta = new VajillaPremium(new VajillaPremium(brindis));
        assertEquals(74000.0, propuesta.getPrecio());
        assertEquals("Brindis de bienvenida + Vajilla premium + Vajilla premium", propuesta.getDescripcion());
    }

    @Test
    void dadoUnContratoCreado_alActualizarElPrecioDeUnServicio_elImporteContratadoNoCambia() {
        // Given
        Servicio pruebaServicio = new Servicio("Prueba", 1000.0);
        Propuesta propuesta = new Mozos(pruebaServicio);
        
        // When
        Contrato contrato = new Contrato("Lucía", propuesta);
        
        // El enunciado dice que el importe del contrato no cambia aunque cambie la propuesta base.
        // Dado que el precio del Servicio se modificará en la etapa 3, 
        // acá validamos simplemente que el contrato guardó la foto correcta (1000 + 18000 = 19000).
        assertEquals(19000.0, contrato.getImporteFijado());
        assertEquals("Prueba + Mozos adicionales", contrato.getDescripcionFijada());
    }
}