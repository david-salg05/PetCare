package Main;

import Entity.Mascota;
import service.MascotaService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    //Creo el scanner global para que pueda ser llamado desde cualquier metodo
    public static final Scanner sc = new Scanner(System.in);

    //Variable global para comprobar los requisitos de cada mascota
    public static final MascotaService ms = new MascotaService();

    public static void main(String[] args) {

        //Variables
        int op = 0;

        do {
            //Imprimo las opciones que hay
            System.out.print("========================================\n" +
                    "SISTEMA VETERINARIO PETCARE (BDOO)\n" +
                    "========================================\n" +
                    "1. Módulo de Mascotas\n" +
                    "2. Módulo de Veterinarios\n" +
                    "3. Salir\n" +
                    "========================================\n");

            op = comprobarValor("Introduzca el número del módulo al que desea acceder: ", 1, 3);

            //Ejecuto la opción elegida
            switch (op) {
                case 1 -> moduloMascotas();
                case 2 -> moduloVeterinarios();
                case 3 -> System.out.println("========================================\n" +
                        "SALIENDO DEL SISTEMA VETERINARIO...\n" +
                        "========================================\n");
            }

        } while (op != 3);
    }

    //Funcion que imprime y ejecuta el menu de opciones del modulo de mascotas
    public static void moduloMascotas() {

        //Variables
        int op = 0;

        //Imprimo las opcione que hay
        System.out.print("Módulo de Mascotas\n" +
                "     1. Registrar mascota.\n" +
                "     2. Buscar por microchip.\n" +
                "     3. Listar mascotas.\n" +
                "     4. Modificar datos.\n" +
                "     5. Eliminar mascota.\n\n");

        op = comprobarValor("Introduzca el número de la opción que desea ejecutar: ", 1, 5);

        //Ejecuto la opción elegida
        switch (op) {
            case 1 -> registrarMascota();
            case 2 -> buscarMicrochip();
            case 3 -> listarMascotas();
            case 4 -> modificarDatos();
            case 5 -> eliminarMascota();
        }
    }

    //Funcion que pide los datos de una mascota y la registra
    private static void registrarMascota() {
        //Variables
        String chip = "";
        String nombre = "";
        String especie = "";
        String edad = "";

        //Pido los datos y los guardo en las variables
        System.out.println("Introduce el chip: ");
        chip = sc.nextLine();

        System.out.println("Introduce el nombre: ");
        nombre = sc.nextLine();

        System.out.println("Introduce la especie: ");
        especie = sc.nextLine();

        System.out.println("Introduce la edad: ");
        edad = sc.nextLine();

        //Envio los datos al service para comprobar los requisitos
        if (ms.registrarMascota(chip, nombre, especie, edad)) {
            System.out.println("\nMascota registrada correctamente.\n");
        } else {
            System.out.println("\nLa mascota no se pudo guardar: el chip está vacío o ya registrado, o la edad no es válida.\n");
        }
    }

    //Funcion que devuelve la mascota por su microchip
    public static void buscarMicrochip() {
        //Pido el microchip
        System.out.println("Introduce el numero de microchip: ");
        String chip = sc.nextLine();

        //Pido la mascota al service
        Mascota m = ms.buscarMicrochip(chip);

        //Si existe imprimo su información, si no, el mensaje de error
        if (m != null) {
            System.out.println("\n" + m + "\n");
        } else {
            System.out.println("\nEl chip de la mascota no se encuentra registrado en la BD.\n");
        }
    }

    //Funcion que lista todas las macotas registradas en la bd
    public static void listarMascotas() {
        ms.listarMascotas();
    }

    //funcion que modifica el nombre o la edad de la mascota
    public static void modificarDatos() {

        int op = 0;

        op = comprobarValor("Qué quieres modificar: \n\t 1- Nombre\n\t 2- Edad", 1, 2);

        switch (op) {
            case 1 -> modificarNombre();
            case 2 -> modificarEdad();
        }
    }

    private static void modificarNombre() {
        String chip = "";
        String nombre = "";

        System.out.println("Introduce el chip de la mascota: ");
        chip = sc.nextLine();

        System.out.println("Introduce el nuevo nombre: ");
        nombre = sc.nextLine();

        if (ms.modificarNombre(chip, nombre)) {
            System.out.println("\nNombre modificado correctamente.\n");
        } else {
            System.out.println("\nNo se pudo modificar: no existe ninguna mascota con ese chip.\n");
        }
    }

    private static void modificarEdad() {
        System.out.println("Introduce el chip de la mascota: ");
        String chip = sc.nextLine();

        System.out.println("Introduce la nueva edad: ");
        String edad = sc.nextLine();   // la leo como texto, valida el service

        if (ms.modificarEdad(chip, edad)) {
            System.out.println("\nEdad modificada correctamente.\n");
        } else {
            System.out.println("\nNo se pudo modificar: la edad no es válida o no existe ninguna mascota con ese chip.\n");
        }
    }

    //Funcion que elimina una mascota por el chip que le paso
    public static void eliminarMascota() {
        System.out.println("Introduce el chip de la mascota a eliminar: ");
        String chip = sc.nextLine();

        if (ms.eliminarMascota(chip)) {
            System.out.println("\nMascota eliminada correctamente.\n");
        } else {
            System.out.println("\nNo se pudo eliminar: no existe ninguna mascota con ese chip.\n");
        }
    }

    //Funcion que imprime y ejecuta el menu de opciones del modulo de veterinarios
    public static void moduloVeterinarios() {
        //Variables
        int op = 0;

        //Imprimo las opcione que hay
        System.out.print("Módulo de Veterinarios\n" +
                "     1. Registrar veterinario.\n" +
                "     2. Buscar por colegiado.\n" +
                "     3. Listar veterinarios.\n" +
                "     4. Modificar especialidad.\n" +
                "     5. Eliminar veterinario.\n");

        op = comprobarValor("Introduzca el número de la opción que desea ejecutar: ", 1, 5);

        //Ejecuto la opción elegida
        switch (op) {
            //case 1 ->
            //case 2 ->
            //case 3 ->
            //case 4 ->
            //case 5 ->
        }
    }

    //Funcion para comprobar que los valores introducidos esten en el rango y sean un numero
    public static int comprobarValor(String mensaje, int min, int max) {
        int res = 0;

        do {
            try {
                System.out.println(mensaje);
                res = sc.nextInt();
                sc.nextLine();   // limpio el salto de línea que deja nextInt

                if (res < min || res > max) {
                    System.out.println("El numero debe estar entre " + min + " y " + max);
                }

            } catch (InputMismatchException IME) {
                System.out.println("No has introducido un numero");
                sc.nextLine();
            }
        } while (res < min || res > max);

        return res;
    }

}