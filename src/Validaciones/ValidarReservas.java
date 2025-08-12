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
