/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import Entidades.ContratoAlquiler;
import Entidades.EstadoContrato;
import Entidades.Vehiculos;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author je110
 */
public class ValidarContratos {
    
    public static boolean existeAlquilerActivoSolapado(List<ContratoAlquiler> contratosActivos, Vehiculos vehiculo, LocalDate fechaInicioNueva, LocalDate fechaFinNueva) {
    for (ContratoAlquiler contrato : contratosActivos) {
        if (contrato.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca()) && contrato.getEstadoContrato() == EstadoContrato.ACTIVO){

            LocalDate inicioExistente = contrato.getFechaInicial();
            LocalDate finExistente = contrato.getFechaFinal();

            boolean rango = !fechaInicioNueva.isAfter(finExistente) && !fechaFinNueva.isBefore(inicioExistente);
            if (rango) {
                return true;  
            }
        }
    }
    return false; 
}

}
