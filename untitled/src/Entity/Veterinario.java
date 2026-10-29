package Entity;

public class Veterinario {

    //Variables de cada veterinario
    private int id;
    private String numColegiado;
    private String nombre;
    private String especialidad;

    //Constructor de veterinario con parámetros
    public Veterinario(int id, String numColegiado, String nombre, String especialidad) {
        this.id = id;
        this.numColegiado = numColegiado;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    //Constructor de veterinario vacío
    public Veterinario() {
    }

    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodo toString para imprimir toda la información de los veterinarios
    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + id +
                ", numColegiado='" + numColegiado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
