package ar.edu.unahur.obj2.catering;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Etapa3Test {

    private GestorCarta gestor;
    private Menu menuParrillero;
    private Servicio mesaDeEntradas;
    private Servicio parrillada;
    private Servicio mesaDulce;
    private Servicio barraDeTragos;
    private Servicio fotografo;

    @BeforeEach
    void setUp() {
        gestor = new GestorCarta();
        mesaDeEntradas = new Servicio("Mesa de entradas", 80000.0);
        parrillada = new Servicio("Parrillada completa", 240000.0);
        mesaDulce = new Servicio("Mesa dulce", 60000.0);
        barraDeTragos = new Servicio("Barra de tragos", 150000.0);
        fotografo = new Servicio("Fotógrafo", 100000.0);

        menuParrillero = new Menu("Menú Parrillero", 10.0);
        menuParrillero.agregarPropuesta(mesaDeEntradas);
        menuParrillero.agregarPropuesta(parrillada);
        menuParrillero.agregarPropuesta(mesaDulce);
    }

    @Test
    void dadoUnGestorConHistorialVacio_alRevertir_lanzaNadaParaRevertirException() {
        assertThrows(NadaParaRevertirException.class, () -> gestor.revertir());
    }

    @Test
    void dadoUnMenu_alQuitarUnaPropuestaNoIncluida_lanzaPropuestaNoIncluidaException() {
        Modificacion quitarInvalido = new QuitarPropuesta(menuParrillero, barraDeTragos);
        assertThrows(PropuestaNoIncluidaException.class, () -> gestor.aplicar(quitarInvalido));
    }

    @Test
    void dadaUnaModificacionDePrecio_alAplicarYRevertir_elPrecioVuelveAlOriginal() {
        Modificacion actualizar = new ActualizarPrecio(parrillada, 260000.0);
        
        gestor.aplicar(actualizar);
        assertEquals(260000.0, parrillada.getPrecio());
        
        gestor.revertir();
        assertEquals(240000.0, parrillada.getPrecio());
    }

    @Test
    void dadaUnaSecuenciaInmediata_alRevertirPasoAPaso_losPreciosSeDeshacenCorrectamente() {
        // Paso 0: Menú 342.000
        Modificacion agregarFoto = new AgregarPropuesta(menuParrillero, fotografo);
        Modificacion actualizarParrilla = new ActualizarPrecio(parrillada, 260000.0);
        Modificacion quitarDulce = new QuitarPropuesta(menuParrillero, mesaDulce);

        gestor.aplicar(agregarFoto); // Paso 1: 432.000
        assertEquals(432000.0, menuParrillero.getPrecio());

        gestor.aplicar(actualizarParrilla); // Paso 2: 450.000
        assertEquals(450000.0, menuParrillero.getPrecio());

        gestor.aplicar(quitarDulce); // Paso 3: 396.000
        assertEquals(396000.0, menuParrillero.getPrecio());

        gestor.revertir(); // Paso 4
        assertEquals(450000.0, menuParrillero.getPrecio());

        gestor.revertir(); // Paso 5
        assertEquals(432000.0, menuParrillero.getPrecio());

        gestor.revertir(); // Paso 6
        assertEquals(342000.0, menuParrillero.getPrecio());
    }

    @Test
    void dadasDosActualizacionesDelMismoServicio_alAplicarlasYRevertirlas_seRespetanLosValoresAnteriores() {
        Modificacion act1 = new ActualizarPrecio(parrillada, 260000.0);
        Modificacion act2 = new ActualizarPrecio(parrillada, 300000.0);

        gestor.aplicar(act1);
        gestor.aplicar(act2);
        assertEquals(300000.0, parrillada.getPrecio());

        gestor.revertir();
        assertEquals(260000.0, parrillada.getPrecio());

        gestor.revertir();
        assertEquals(240000.0, parrillada.getPrecio());
    }

    @Test
    void dadoUnLoteDeModificaciones_alAplicarloExitosamente_seRegistranTodasYSePuedenRevertir() {
        List<Modificacion> lote = Arrays.asList(
            new AgregarPropuesta(menuParrillero, fotografo),
            new ActualizarPrecio(parrillada, 260000.0),
            new QuitarPropuesta(menuParrillero, mesaDulce)
        );

        gestor.aplicarLote(lote);
        assertEquals(396000.0, menuParrillero.getPrecio());

        gestor.revertir(); // Revierte QuitarPropuesta
        assertEquals(450000.0, menuParrillero.getPrecio());
    }

    @Test
    void dadoUnLoteConFalla_alAplicarlo_fallaYElCatalogoQuedaIntactoSinRegistrarEnElHistorial() {
        gestor.aplicar(new AgregarPropuesta(menuParrillero, fotografo)); // Se aplica y queda en historial
        
        // El lote falla en la segunda modificación porque barraDeTragos no está en el menú
        List<Modificacion> loteQueFalla = Arrays.asList(
            new ActualizarPrecio(parrillada, 260000.0),
            new QuitarPropuesta(menuParrillero, barraDeTragos) 
        );

        assertThrows(PropuestaNoIncluidaException.class, () -> gestor.aplicarLote(loteQueFalla));

        // El precio de la parrillada vuelve a 240.000 (el lote revirtió la actualización)
        assertEquals(240000.0, parrillada.getPrecio());
        
        // El precio del menú parrillero debe contemplar sólo al Fotógrafo (432.000)
        assertEquals(432000.0, menuParrillero.getPrecio());

        // Al revertir, debe deshacer únicamente el "Agregar Fotógrafo" de antes del lote
        gestor.revertir();
        assertEquals(342000.0, menuParrillero.getPrecio());
        
        // Y el historial debe quedar vacío
        assertThrows(NadaParaRevertirException.class, () -> gestor.revertir());
    }
}