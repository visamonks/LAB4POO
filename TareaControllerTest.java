import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TareaControllerTest {
    private TareaController controlador;
    private AlmacenarTareas almacenamiento;

    @BeforeEach
    void setUp() {
        almacenamiento = new AlmacenamientoMemoria();
        controlador = new TareaController(almacenamiento);
    }

    @Test
    void testAgregarVariosElementosYRevisarIds() {
        Tarea t = controlador.agregarTarea("atender estudiantes","Ximena");
        Tarea t2 = controlador.agregarTarea("atender estudiantes","Luis Diego");
        Tarea t3 = controlador.agregarTarea("atender estudiantes","william");
        assertEquals(0, t.getId());
        assertEquals(1, t2.getId());
        assertEquals(2, t3.getId());
        assertNotEquals(t.getId(), t2.getId());
        assertNotNull(almacenamiento.buscarPorId(2));
        assertSame(t3, almacenamiento.buscarPorId(2));
    }

    @Test
    void validarCambioDeEstado() {
        Tarea t3 = controlador.agregarTarea("atender estudiantes","william");
        assertFalse(t3.getCompletada());
        controlador.completarTarea(t3.getId());
        assertTrue(t3.getCompletada());
    }

    @Test
    void testCompletarTareaInexistenteLanzaExcepcion() {
        controlador.agregarTarea("atender estudiantes","Ximena");
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            controlador.completarTarea(99);
        });
        assertEquals("No hay una tarea con el ID: 99", e.getMessage());
    }

    @Test
    void testCompletarTareaYaCompletadaLanzaExcepcion() {
        Tarea t = controlador.agregarTarea("atender estudiantes","Luis Diego");
        controlador.completarTarea(t.getId());
        assertThrows(IllegalStateException.class, () -> {
            controlador.completarTarea(t.getId());
        });
    }

    @Test
    void testCompletarIdIgualAlContadorLanzaExcepcion() {
        controlador.agregarTarea("a", "1");
        controlador.agregarTarea("b", "2");
        controlador.agregarTarea("c", "3");
        assertThrows(IllegalArgumentException.class, () -> controlador.completarTarea(3));
    }

    @Test
    void testEliminarTareaExistente() {
        Tarea t = controlador.agregarTarea("atender estudiantes","Ximena");
        assertNotNull(almacenamiento.buscarPorId(t.getId()));
        controlador.eliminarTarea(t.getId());
        assertNull(almacenamiento.buscarPorId(t.getId()));
        assertEquals(0, almacenamiento.obtenerTodas().size());
    }

    @Test
    void testEliminarTareaInexistenteLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.eliminarTarea(99);
        });
    }

    @Test
    void testEliminarDosVecesLaMismaTareaLanzaExcepcion() {
        Tarea t = controlador.agregarTarea("atender estudiantes","william");
        controlador.eliminarTarea(t.getId());
        assertThrows(IllegalArgumentException.class, () -> controlador.eliminarTarea(t.getId()));
    }
}
