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

        op = comprobarValor("Introduzca el número del módulo al que desea acceder: ", 1, 5);

        //Ejecuto la opción elegida
        switch (op) {
            case 1 -> registrarMascota();
            case 2 -> buscarMicrochip();
            case 3 ->
            case 4 ->
            case 5 ->
        }
    }

    private static void registrarMascota() {
        //Variables
        String chip = "";
        String nombre = "";
        String especie = "";
        int edad = 0;

        //Pido los datos y los guardo en las variales
        System.out.println("Introduce el chip: ");
        chip = sc.nextLine();

        System.out.println("Introduce el nombre: ");
        nombre = sc.nextLine();

        System.out.println("Introduce la especie: ");
        especie = sc.nextLine();

        System.out.println("Introduce la edad: ");
        edad = sc.nextInt();

        //Guardo la nueva mascota
        Mascota m = new Mascota(chip, nombre, especie, edad);

        //Envio la mascota para comprobar los requsitos de la funcion
        if(ms.registrarMascota(m)){
            System.out.println("\nMascota registrada correctamente.\n");
        }else{
            System.out.println("\nLa mascota no se pudo guardar porque el chip introducido ya está registrado en la BD.\n");
        }

        //Imprimo un mensaje final
        System.out.println();
    }

    //Funcion que devuelve la mascota por su microchip
    public static void buscarMicrochip(){
        //Creo una nueva mascota
        Mascota m = new Mascota();

        //Pido el microchip
        System.out.println("Introduce el numero de microchip: ");

        //Guardo el chip introducido en la nueva mascota
        m.setChip(sc.nextLine());

        //Envio la nueva mascota para comprobar si el chip es correcto e imprimo la informacion
        if(ms.buscarMicrochip(m)){
            System.out.println(m);
        }else{
            System.out.println("El chip de la mascota no se encuentra registrado en la BD.\n");
        }

    }

    //Funcion qque imprime y ejecuta el menu de opciones del modulo de veterinarios
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

        op = comprobarValor("Introduzca el número del módulo al que desea acceder: ", 1, 5);

        //Ejecuto la opción elegida
        switch (op) {
            case 1 ->
            case 2 ->
            case 3 ->
            case 4 ->
            case 5 ->
        }
    }

    //Funcion para comprobar que los valores introducidos estean en el rango y sean un numero
    public static int comprobarValor(String mensaje, int min, int max) {
        int res = 0;

        do {
            try {
                System.out.println(mensaje);
                res = sc.nextInt();

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