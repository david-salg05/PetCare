package dao;

import Entity.Mascota;
import Entity.Veterinario;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import util.ConexionDb4o;

import java.util.ArrayList;

public class VeterinarioDAO {

    //Hago la conexion global con la base de datos
    static ConexionDb4o conexionBD = new ConexionDb4o();

    //Funcion que registra el veterinario si el numero de colegiado es único
    public boolean registrarVeterinario(Veterinario v) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Compruebo si el numero de colegiado de mi veterinario está registrado
        if (existeNumColegiado(bd, v.getNumColegiado())) {
            bd.close();
            return false;
        }

        //Calculo el id del nuevo veterinario y lo asigno
        int id = calcularID(bd);
        v.setId(id);

        //Guardo el veterinario
        bd.store(v);

        //Cierro la bd
        bd.close();

        return true;
    }

    //Funcion que comprueba si ya existe el numero de colegiado pasado como parametro
    private static boolean existeNumColegiado(ObjectContainer bd, String numColegiado) {
        //Abro la lista de veterinarios
        ObjectSet<Veterinario> veterinarios = bd.query(Veterinario.class);

        //Recorro la lista y compruebo si su numero de colegiado corresponde con el que he pasado
        for (Veterinario v : veterinarios) {
            if (v.getNumColegiado().equalsIgnoreCase(numColegiado)) {
                return true;
            }
        }
        return false;
    }

    //Funcion que calcula el id de un veterinario sumando 1 al ultimo id creado
    private int calcularID(ObjectContainer bd) {

        //Abro la lista de todos los veterinarios de la bd
        ObjectSet<Veterinario> todosVeterinarios = bd.query(Veterinario.class);

        int maxId = 0;

        //Recorro la bd guardando el id mayor
        for (Veterinario v : todosVeterinarios) {
            if (v.getId() > maxId) {
                maxId = v.getId();
            }
        }

        //Devuelvo el id anterior + 1
        return maxId + 1;
    }

    //Funcion que devuelve el veterinario con el numero de colegiado indicado, o null si no existe
    public Veterinario buscarColegiado(String numeroColegiado) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Recorro los veterinarios buscando el que tenga ese numero de colegiado
        ObjectSet<Veterinario> veterinarios = bd.query(Veterinario.class);

        for (Veterinario v : veterinarios) {
            if (v.getNumColegiado().equalsIgnoreCase(numeroColegiado)) {
                bd.close();
                return v;   // lo devuelvo con todos sus datos
            }
        }

        //Si llego aquí no existe
        bd.close();
        return null;
    }

    //Funcion que lista los veterinarios guardados en la bd
    public ArrayList<Veterinario> listarVeterinarios() {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Guardo en el arrayList todos los veterinarios
        ArrayList<Veterinario> listaVeterinarios = new ArrayList<>(bd.query(Veterinario.class));

        //Cierro la bd y devuelvo la lista
        bd.close();
        return listaVeterinarios;
    }

    //Funcion que modifica la especialidad del veterinario cuyo numero de colegiado se pasa por parametro
    public boolean modificarEspecialidad(String numColegiado, String especialidad) {
        //Abro la conexion
        ObjectContainer bd = conexionBD.abrirConexion();

        //Recorro los veterinarios buscando el que tenga ese numero de colegiado
        ObjectSet<Veterinario> veterinarios = bd.query(Veterinario.class);

        for (Veterinario v : veterinarios) {
            if (v.getNumColegiado().equalsIgnoreCase(numColegiado)) {
                //cambio la especialidad, guardo y cierro
                v.setEspecialidad(especialidad);
                bd.store(v);
                bd.commit();
                bd.close();
                return true;
            }
        }

        //no existe ningun veterinario con ese numero de colegiado
        bd.close();
        return false;
    }

    //Funcion que elimina de la bd el veterinario cuyo numero de colegiado se pasa por parametro
    public boolean eliminarVeterinario(String numColegiado) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Recorro los veterinarios buscando el que tenga ese numero de colegiado
        ObjectSet<Veterinario> veterinarios = bd.query(Veterinario.class);

        for (Veterinario v : veterinarios) {
            if (v.getNumColegiado().equalsIgnoreCase(numColegiado)) {
                //lo elimino, confirmo y cierro
                bd.delete(v);
                bd.commit();
                bd.close();
                return true;
            }
        }

        //no existe ningun veterinario con ese numero de colegiado
        bd.close();
        return false;
    }
}
