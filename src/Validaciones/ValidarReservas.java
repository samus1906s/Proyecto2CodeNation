/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import Entidades.Reserva;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import Entidades.Clientes;
import Entidades.Vehiculos;



/**
 *
 * @author Eduard Salas Murillo
 */
public abstract class ValidarReservas {
    
    public static boolean ClienteRegistrado(Clientes cedula, Map<String,Clientes> clientes) {
        return clientes.containsKey(cedula);
    }

    public static boolean VehiculoRegistrado(Vehiculos placa, Map<String,Vehiculos> vehiculos) {
        return vehiculos.containsKey(placa);
    }

    public static boolean FechaInicioValida(LocalDate fechaInicio) {
        return !fechaInicio.isBefore(LocalDate.now());
    }

    public static boolean FechaFinPosterior(LocalDate fechaInicio, LocalDate fechaFin) {
        return fechaFin.isAfter(fechaInicio);
    }

    public static boolean DuracionValida(LocalDate fechaInicio, LocalDate fechaFin) {
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        return dias <= 30;
    }

    public static boolean VehiculoDisponible(
            Vehiculos placa,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Map<Integer,Reserva> reservas
    ) {
        for (Reserva r : reservas.values()) {
            if (r.getPlacaVehiculo().equals(placa)) {
                boolean solapado = !(fechaFin.isBefore(r.getFechaInicio()) || fechaInicio.isAfter(r.getFechaFin()));
                if (solapado) {
                    return false;
                }
            }
        }
        return true;
    }
}
