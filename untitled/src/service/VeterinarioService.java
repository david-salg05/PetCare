package service;

import Entity.Mascota;
import Entity.Veterinario;
import dao.MascotaDAO;
import dao.VeterinarioDAO;

import java.util.ArrayList;

public class VeterinarioService {

    //Creo una variable global para pasar el veterinario a la bd
    public static final VeterinarioDAO vd = new VeterinarioDAO();

    //Funcion que comprueba los requisitos para registrar una nueva mascota
    public boolean registrarVeterinario(String numColegiado, String nombre, String especialidad){

        //Compruebo que el numero de colegiado no esté vacío
        if (numColegiado == null || numColegiado.trim().isEmpty()) {
            return false;
        }

        //Si los datos del veterinario son validos, compruebo que no exista el numero de colegiado y devuelvo el resultado
        Veterinario v = new Veterinario(numColegiado.trim(), nombre, especialidad);
        return vd.registrarVeterinario(v);
    }

    //Funcion que devuelve el veterinario con el numero de colegiado indicado, o null si no existe
    public Veterinario buscarColegiado(String numeroColegiado) {
        return vd.buscarColegiado(numeroColegiado.trim());
    }

    //Funcion que imprime todos los veterinarios de la bd
    public void listarVeterinarios() {
        //Creo un arrayList con los veterinarios de la bd
        ArrayList<Veterinario> listaVeterinarios = vd.listarVeterinarios();

        //Si no hay veterinarios registrados imprimo el mensaje
        if (listaVeterinarios.isEmpty()) {
            System.out.println("\nNo hay mascotas registradas.\n");
            return;
        }

        //En caso contrario, recorro la lista de veterinarios e imprimo su información
        for (Veterinario v : listaVeterinarios) {
            System.out.println(v);
        }
    }

    //Funcion que modifica la especialidad del veterinario con el numero de colegiado indicado
    public boolean modificarEspecialidad(String numColegiado, String especialidad) {
        return vd.modificarEspecialidad(numColegiado.trim(), especialidad);
    }

    //Funcion que elimina el veterinario con el numero de colegiado indicado
    public boolean eliminarVeterinario(String numColegiado) {
        return vd.eliminarVeterinario(numColegiado.trim());
    }
}
