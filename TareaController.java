import java.util.List;

public class TareaController {
    private AlmacenarTareas almacenamiento;
    private Notificador notificador;
    private int contadorTareas;

    public TareaController(AlmacenarTareas almacenamiento, Notificador notificador) {
        this.almacenamiento = almacenamiento;
        this.notificador = notificador;
        this.contadorTareas = 0;
    }

    public TareaController(AlmacenarTareas almacenamiento) {
        this(almacenamiento, mensaje -> { });
    }

    public Tarea agregarTarea(String titulo, String descripcion) {
        Tarea nueva = new Tarea(contadorTareas++, titulo, descripcion);
        almacenamiento.guardar(nueva);
        notificador.enviarMensaje("Se agregó la tarea con ID " + nueva.getId());
        return nueva;
    }

    public void completarTarea(int id) {
        Tarea t = almacenamiento.buscarPorId(id);
        if (t == null) {
            throw new IllegalArgumentException("No hay una tarea con el ID: " + id);
        }
        if (t.getCompletada()) {
            throw new IllegalStateException("La tarea ya estaba completada.");
        }
        t.setCompletada(true);
        notificador.enviarMensaje("Se completó la tarea con ID " + id);
    }

    public void eliminarTarea(int id) {
        boolean eliminada = almacenamiento.eliminar(id);
        if (!eliminada) {
            throw new IllegalArgumentException("No hay una tarea con el ID: " + id);
        }
        notificador.enviarMensaje("Se eliminó la tarea con ID " + id);
    }

    public List<Tarea> obtenerTareas() {
        return almacenamiento.obtenerTodas();
    }
}
