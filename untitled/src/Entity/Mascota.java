package Entity;

public class Mascota {

    //Variables de cada mascota
    private int id;
    private String chip;
    private String nombre;
    private String especie;
    private int edad;

    //Constructor de mascota con parámetros
    public Mascota(String chip, String nombre, String especie, int edad) {
        this.chip = chip;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
    }

    //Constructor de mascota vacío
    public Mascota() {
    }

    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    //Metodo toString para imprimir información de cada mascota
    @Override
    public String toString() {
        return "Mascota{" +
                "id= " + id +
                ", chip= " + chip +
                ", nombre= '" + nombre + '\'' +
                ", especie= '" + especie + '\'' +
                ", edad= " + edad +
                '}';
    }
}