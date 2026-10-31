package service;

import Entity.Mascota;
import dao.MascotaDAO;

import java.util.ArrayList;

public class MascotaService {

    //Creo una variable global para pasar la mascota a la bd
    public static final MascotaDAO md = new MascotaDAO();

    //Funcion que comprueba los requisitos para registrar una nueva mascota
    public boolean registrarMascota(String chip, String nombre, String especie, String edadIn) {
        int edad;

        //Compruebo que el chip no esté vacío
        if (chip == null || chip.trim().isEmpty()) {
            return false;
        }

        //Compruebo que la edad introducida sea un numero
        try {
            edad = Integer.parseInt(edadIn.trim());
        } catch (NumberFormatException e) {
            return false;
        }

        //Compruebo tambien que la edad no sea un numero negativo
        if (edad < 0) {
            return false;
        }

        //Si los datos de la mascota son validos, compruebo que no exista el chip y devuelvo el resultado
        Mascota m = new Mascota(chip.trim(), nombre, especie, edad);
        return md.registrarMascota(m);
    }

    //Funcion que devuelve la mascota con el chip indicado, o null si no existe
    public Mascota buscarMicrochip(String chip) {
        return md.buscarMicrochip(chip.trim());
    }

    //Funcion que imprime todas las mascotas de la bd
    public void listarMascotas() {
        //Creo un arrayList con las mascotas de la bd
        ArrayList<Mascota> listaMascotas = md.listarMascotas();

        //Si no hay mascotas registradas imprimo el mensaje
        if (listaMascotas.isEmpty()) {
            System.out.println("\nNo hay mascotas registradas.\n");
            return;
        }

        //En caso contrario, recorro la lista de mascotas e imprimo su información
        for (Mascota m : listaMascotas) {
            System.out.println(m);
        }
    }

    //Funcion que modifica el nombre de la mascota con el chip indicado
    public boolean modificarNombre(String chip, String nombre) {
        return md.modificarNombre(chip.trim(), nombre);
    }

    //Funcion que valida la edad y modifica la mascota con el chip indicado
    public boolean modificarEdad(String chip, String edadTexto) {
        int edad;

        try {
            edad = Integer.parseInt(edadTexto.trim());
        } catch (NumberFormatException e) {
            return false;
        }

        if (edad < 0) {
            return false;
        }

        return md.modificarEdad(chip.trim(), edad);
    }

    //Funcion que elimina la mascota con el chip indicado
    public boolean eliminarMascota(String chip) {
        return md.eliminarMascota(chip.trim());
    }
}