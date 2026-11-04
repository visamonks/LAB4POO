import java.util.List;
import java.util.Scanner;

public class VistaTerminal {
    private Scanner scanner;
    private TareaController controlador;

    public VistaTerminal(TareaController controlador){
        this.scanner = new Scanner(System.in);
        this.controlador = controlador;
    }

    public void mostrarMenu() {

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- todolist Nuestra---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Completar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Ver tareas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Titulo de la tarea: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descripción de la tarea: ");
                    String desc = scanner.nextLine();
                    Tarea nueva = controlador.agregarTarea(titulo, desc);
                    System.out.println("Tarea agregada con ID " + nueva.getId() + ".");
                    break;

                case "2":
                    try {
                        System.out.print("ID de la tarea a completar: ");
                        int idACompletar = Integer.parseInt(scanner.nextLine().trim());
                        controlador.completarTarea(idACompletar);
                        System.out.println("Tarea completada.");
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un número entero.");
                    }
                    catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        System.out.print("ID de la tarea a eliminar: ");
                        int idAEliminar = Integer.parseInt(scanner.nextLine().trim());
                        controlador.eliminarTarea(idAEliminar);
                        System.out.println("Tarea eliminada.");
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un número entero.");
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    List<Tarea> tareas = controlador.obtenerTareas();
                    if (tareas.isEmpty()) {
                        System.out.println("No hay tareas.");
                    }
                    for (Tarea t : tareas) {
                        System.out.println(t);
                    }
                    break;

                case "5":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
