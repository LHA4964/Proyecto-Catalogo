package interfaz;
import dominio.*;
import java.util.Scanner;

public class Interfaz {
    private Catalogo catalogo;
    private Scanner sc;

    public Interfaz() {
        this.catalogo = Catalogo.leer();
        this.sc = new Scanner(System.in);
    }

    public String[] instruccion() {
        String instruccion = sc.nextLine();
        return instruccion.split(",");
    }

    public void menu() {
        help();

        while (true) {
            System.out.print("\nEscriba la instrucción: ");
            String[] instr = instruccion();

            switch (instr[0].toLowerCase()) {
                case "help":
                    help();
                    break;
                case "list":
                    System.out.println(catalogo.list());
                    break;
                case "info":
                    if (instr.length == 2){
                        System.out.println(catalogo.info(Integer.parseInt(instr[1])));
                    } else {
                        System.out.println("Error para comando (info). Ejemplo: info,Id");
                    }
                    break;
                case "add":
                    if (instr.length == 7) {
                        aniadir(instr[1], instr[2], instr[3], Integer.parseInt(instr[4]), Integer.parseInt(instr[5]), Integer.parseInt(instr[6]));
                    } else {
                        System.out.println("Error para comando (add). Ejemplo: add,Marca,CPU,GPU,RAM,Precio,Id");
                    }
                    break;
                case "remove":
                    if (instr.length == 2) {
                        remove(Integer.parseInt(instr[1]));
                    } else {
                        System.out.println("Error para comando (remove). Ejemplo: remove,Id");
                    }
                    break;
                case "modify":
                    if (instr.length == 4) {
                        modificar(Integer.parseInt(instr[1]), instr[2], instr[3]);
                    } else {
                        System.out.println("Error para comando (modify). Ejemplo: modify,id,Atributo,NuevoValor");
                    }
                    break;
                case "exit":
                    catalogo.grabar();
                    System.out.println("Saliendo");
                    return;
                default:
                    System.out.println("\nInstruccion no valida\n");
                    help();
                    break;
            }
        }
    }

    public void help() {
        System.out.println("help: muestra este mensaje de ayuda\n" +
                "list: muestra información reducida de todos los ordenadores\n" +
                "info: muestra información detallada de un ordenador (depende de id)\n" +
                "add: añade un nuevo ordenador al catalogo\n" +
                "remove: elimina un ordenador del catalogo\n" +
                "modify: modifica un ordenador del catalogo\n" +
                "\t Opciones: marca, cpu, gpu, ram, precio\n" +
                "exit: salir y guardar el programa");
    }

    public void aniadir(String marca, String cpu, String gpu, int ram, int precio, int id) {
        try {
            catalogo.annadirOrdenador(new Ordenador(marca, cpu, gpu, ram, precio, id));
            System.out.println("Ordenador añadido con id: " + id);
        } catch (ErrorOrdenadorDuplicado e) {
            id = id + 1;
            aniadir(marca, cpu, gpu, ram, precio, id);
        }
    }

    public void remove(int id) {
        try {
            catalogo.removeOrdenador(id);
            System.out.println("Ordenador borrado");
        } catch (ErrorOrdenadorNoEncontrado e) {
            System.out.println("Ordenador con id " + e.getOrdenador() + " no encontrado");
        }
    }

    private void modificar(int id, String atributo, String nuevo) {
        if (atributo.equals("marca")||atributo.equals("cpu")||atributo.equals("gpu")||atributo.equals("ram")||atributo.equals("precio")) {
            try {
                catalogo.modificarOrdenador(id, atributo, nuevo);
                System.out.println("Contacto modificado.");
            }catch(ErrorOrdenadorNoEncontrado e){
                System.out.println("El contacto " + e.getOrdenador() +" no se ha encontrado");
            }
        } else {
            System.out.println("Atributo no valido");
        }
    }
}