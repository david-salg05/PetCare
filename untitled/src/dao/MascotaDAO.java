package dao;

import Entity.Mascota;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import util.ConexionDb4o;

public class MascotaDAO {

    //Hago la conexion global con la base de datos
    ConexionDb4o conexionBD = new ConexionDb4o();

    //Funcion que comprueba si el chip es único
    public boolean registrarMascota(Mascota m){
        //Abro la bd
        ObjectContainer bd = conexionBD.abrirConexion();

        //Compruebo si el chip de mi mascota está registrado
        if(existeChip(bd, m.getChip())){
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
    private boolean existeChip(ObjectContainer bd, int chip) {
        ObjectSet<Mascota> mascotas = bd.query(Mascota.class);

        //Recorro la lista de mascotas y compruebo si su chip corresponde con el que he pasado
        for (Mascota m : mascotas){
            if (m.getChip() == chip){
                return true;
            }
        }
        return false;
    }

    //Funcion que calcula el id de una mascota sumando 1 al ultimo id creado
    private int calcularID(ObjectContainer bd) {

        //Abro la lista de todas las mascotas de la bd
        ObjectSet<Mascota> todasMascotas = bd.queryByExample(new Mascota());

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
}
