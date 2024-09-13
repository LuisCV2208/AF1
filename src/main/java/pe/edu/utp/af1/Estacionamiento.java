package pe.edu.utp.af1;

import java.sql.Timestamp;

public class Estacionamiento {
    private int idEstacionamiento;
    private Timestamp fechaHoraIngreso;
    private Timestamp fechaHoraSalida;
    private double montoCobro;
    private String comentario;
    private int idVehiculo;

    public Estacionamiento(int idEstacionamiento, Timestamp fechaHoraIngreso, Timestamp fechaHoraSalida, double montoCobro, String comentario, int idVehiculo) {
        this.idEstacionamiento = idEstacionamiento;
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraSalida = fechaHoraSalida;
        this.montoCobro = montoCobro;
        this.comentario = comentario;
        this.idVehiculo = idVehiculo;
    }

    public Estacionamiento(Timestamp fechaHoraIngreso, Timestamp fechaHoraSalida, double montoCobro, String comentario, int idVehiculo) {
        this.fechaHoraIngreso = fechaHoraIngreso;
        this.fechaHoraSalida = fechaHoraSalida;
        this.montoCobro = montoCobro;
        this.comentario = comentario;
        this.idVehiculo = idVehiculo;
    }


    public int getIdEstacionamiento() {
        return idEstacionamiento;
    }

    public void setIdEstacionamiento(int idEstacionamiento) {
        this.idEstacionamiento = idEstacionamiento;
    }

    public Timestamp getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(Timestamp fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public Timestamp getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(Timestamp fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public double getMontoCobro() {
        return montoCobro;
    }

    public void setMontoCobro(double montoCobro) {
        this.montoCobro = montoCobro;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
}
