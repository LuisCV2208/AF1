package pe.edu.utp.af1;

public class Conductor {
    private int idConductor;
    private String nombre;
    private String dni;

    public Conductor(int idConductor, String nombre, String dni) {
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.dni = dni;
    }

    public Conductor(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}