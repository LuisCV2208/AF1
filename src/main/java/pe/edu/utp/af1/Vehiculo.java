package pe.edu.utp.af1;

public class Vehiculo {
    private int idVehiculo;
    private String placa;
    private String tipo;
    private int idConductor;

    public Vehiculo(int idVehiculo, String placa, String tipo, int idConductor) {
        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.idConductor = idConductor;
    }

    public Vehiculo(String placa, String tipo, int idConductor) {
        this.placa = placa;
        this.tipo = tipo;
        this.idConductor = idConductor;
    }


    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }
}
