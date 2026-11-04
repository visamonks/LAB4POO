public class Main {
    public static void main(String args[]){
        AlmacenarTareas almacenamiento = new AlmacenamientoMemoria();
        Notificador notificador = new NotificadorConsola();
        TareaController controlador = new TareaController(almacenamiento, notificador);

        VistaTerminal vista = new VistaTerminal(controlador);
        vista.mostrarMenu();
    }
}
