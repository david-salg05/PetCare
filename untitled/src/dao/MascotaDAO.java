package dao;

import Entity.Mascota;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import util.ConexionDb4o;

import java.util.ArrayList;

public class MascotaDAO {

    //Hago la conexion global con la base de datos
    static ConexionDb4o conexionBD = new ConexionDb4o();

    //Funcion que registra la mascota si el chip es único
    public boolean registrarMascota(Mascota m) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Compruebo si el chip de mi mascota está registrado
        if (existeChip(bd, m.getChip())) {
            bd.close();
            return false;
        }

        //Calculo el id de la nueva mascota y lo asigno
        int id = calcularID(bd);
        m.setId(id);

        //Guardo la mascota
        bd.store(m);

        //Cierro la bd
        bd.close();

        return true;
    }

    //Funcion que comprueba si ya existe el chip pasado como parametro
    private static boolean existeChip(ObjectContainer bd, String chip) {
        //Abro la lista de mascotas
        ObjectSet<Mascota> mascotas = bd.query(Mascota.class);

        //Recorro la lista y compruebo si su chip corresponde con el que he pasado
        for (Mascota m : mascotas) {
            if (m.getChip().equalsIgnoreCase(chip)) {
                return true;
            }
        }
        return false;
    }

    //Funcion que calcula el id de una mascota sumando 1 al ultimo id creado
    private int calcularID(ObjectContainer bd) {

        //Abro la lista de todas las mascotas de la bd
        ObjectSet<Mascota> todasMascotas = bd.query(Mascota.class);

        int maxId = 0;

        //Recorro la bd guardando el id mayor
        for (Mascota m : todasMascotas) {
            if (m.getId() > maxId) {
                maxId = m.getId();
            }
        }

        //Devuelvo el id anterior + 1
        return maxId + 1;
    }

    //Funcion que devuelve la mascota con el chip indicado, o null si no existe
    public Mascota buscarMicrochip(String chip) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Recorro las mascotas buscando la que tenga ese chip
        ObjectSet<Mascota> mascotas = bd.query(Mascota.class);

        for (Mascota m : mascotas) {
            if (m.getChip().equalsIgnoreCase(chip)) {
                bd.close();
                return m;   // la devuelvo con todos sus datos
            }
        }

        //Si llego aquí no existe
        bd.close();
        return null;
    }

    //Funcion que lista las mascotas guardadas en la bd
    public ArrayList<Mascota> listarMascotas() {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Guardo en el arrayList todas las mascotas
        ArrayList<Mascota> listaMascotas = new ArrayList<>(bd.query(Mascota.class));

        //Cierro la bd y devuelvo la lista
        bd.close();
        return listaMascotas;
    }

    //Funcion que modifica el nombre de la mascota cuyo chip se pasa por parametro
    public boolean modificarNombre(String chip, String nombre) {
        //Abro la conexion
        ObjectContainer bd = conexionBD.abrirConexion();

        //Recorro las mascotas buscando la que tenga ese chip
        ObjectSet<Mascota> mascotas = bd.query(Mascota.class);

        for (Mascota m : mascotas) {
            if (m.getChip().equalsIgnoreCase(chip)) {
                //La he encontrado: cambio el nombre, guardo y cierro
                m.setNombre(nombre);
                bd.store(m);
                bd.commit();
                bd.close();
                return true;
            }
        }

        //Si llego aquí no existe ninguna mascota con ese chip
        bd.close();
        return false;
    }

    //Funcion que modifica la edad de la mascota cuyo chip se pasa por parametro
    public boolean modificarEdad(String chip, int edad) {
        //Abro la conexion
        ObjectContainer bd = conexionBD.abrirConexion();

        //Recorro las mascotas buscando la que tenga ese chip
        ObjectSet<Mascota> mascotas = bd.query(Mascota.class);

        for (Mascota m : mascotas) {
            if (m.getChip().equalsIgnoreCase(chip)) {
                //La he encontrado: cambio la edad, guardo y cierro
                m.setEdad(edad);
                bd.store(m);
                bd.commit();
                bd.close();
                return true;
            }
        }

        //Si llego aquí no existe ninguna mascota con ese chip
        bd.close();
        return false;
    }

    //Funcion que elimina de la bd la mascota cuyo chip se pasa por parametro
    public boolean eliminarMascota(String chip) {
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Recorro las mascotas buscando la que tenga ese chip
        ObjectSet<Mascota> mascotas = bd.query(Mascota.class);

        for (Mascota m : mascotas) {
            if (m.getChip().equalsIgnoreCase(chip)) {
                //La he encontrado: la elimino, confirmo y cierro
                bd.delete(m);
                bd.commit();
                bd.close();
                return true;
            }
        }

        //Si llego aquí no existe ninguna mascota con ese chip
        bd.close();
        return false;
    }
}