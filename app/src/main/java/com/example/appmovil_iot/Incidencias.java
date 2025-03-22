package com.example.appmovil_iot;

public class Incidencias {
    private String fecha;
    private String hora;
    private String movimiento;
    private String tipo;

    public Incidencias(String fecha, String hora, String movimiento, String tipo) {
        this.fecha = fecha;
        this.hora = hora;
        this.movimiento = movimiento;
        this.tipo = tipo;
    }

    public  Incidencias(){

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
