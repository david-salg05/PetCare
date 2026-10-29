package service;

import Entity.Mascota;
import dao.MascotaDAO;

public class MascotaService {

    //Creo una variable global paa pasar la mascota a la bd
    public static final MascotaDAO md = new MascotaDAO();

    //Funcion que comprueba los requisitos para registrar una nueva mascota
    public boolean registrarMascota(Mascota m){
        return md.registrarMascota(m);
    }
}
