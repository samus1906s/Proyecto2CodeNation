/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Excepciones.VehiculoExcepciones.EstadoInvalidoExcepcion;
import Excepciones.VehiculoExcepciones.TransicionEstadoNoPermitidoExcepcion;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author je110
 */
public class ContratoAlquiler {
    private int contratoID;
    private Clientes cliente;
    private Vehiculos vehiculo;
    private Reserva reserva;
    private EstadoContrato estadocontrato;
    private LocalDate fechaInicial, fechaFinal;
    private double tarifaDiaria;
    private double montoTotal;
    private int dias;

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public int getDias() {
        return dias;
    }

    public int getContratoID() {
        return contratoID;
    }

    public EstadoContrato getEstadoContrato() {
        return estadocontrato;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setEstadoContrato(EstadoContrato estadocontrato) {
        this.estadocontrato = estadocontrato;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setTarifaDiaria(double tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }

    public ContratoAlquiler(int contratoID, Clientes cliente, Vehiculos vehiculo, Reserva reserva, LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria) {
        this.contratoID = contratoID;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.reserva = reserva;
        this.estadocontrato = EstadoContrato.ACTIVO;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tarifaDiaria = tarifaDiaria;
        this.montoTotal = calcularMontoTotal(tarifaDiaria, dias);
        this.dias = (int) ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
    }
    
    private double calcularMontoTotal(double tarifaDiaria, int dias){
        return montoTotal = tarifaDiaria * dias;
    }
    
    private void inicializarContrato() throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion{
       if (estadocontrato == EstadoContrato.ACTIVO){
           this.vehiculo.setEstado(EstadoVehiculos.EN_ALQUILER);
       }
    }
    
}
