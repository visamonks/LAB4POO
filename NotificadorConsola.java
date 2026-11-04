public class NotificadorConsola implements Notificador {

    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("[Notificación] " + mensaje);
    }
}
