/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import Entidades.Clientes;
import Entidades.Vehiculos;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author je110
 */
public class ValidacionGeneral {
    
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
    
}
