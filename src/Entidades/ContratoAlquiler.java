/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author je110
 */
public class ContratoAlquiler {
    
    private EstadoContrato estadocontrato;
    private LocalDate fechaInicial, fechaFinal;
    private double tarifaDiaria;
    private double montoTotal;
    private int dias;

    public EstadoContrato getContrato() {
        return estadocontrato;
    }

    public LocalDate getFechaI() {
        return fechaInicial;
    }

    public LocalDate getFechaF() {
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

    public void setEstadocontrato(EstadoContrato estadocontrato) {
        this.estadocontrato = estadocontrato;
    }

    public void setFechaI(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public void setFechaF(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setTarifaDiaria(double tarifaDiaria) {
        this.tarifaDiaria = tarifaDiaria;
    }

    public ContratoAlquiler(LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria, double montoTotal, int dias) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tarifaDiaria = tarifaDiaria;
        this.montoTotal = montoTotal;
        this.dias = dias;
        this.estadocontrato = estadocontrato.ACTIVO;
    }
    
    private double CalcularMontoTotal(double tarifaDiaria, int dias){
        return montoTotal = tarifaDiaria * dias;
    }
    
}
