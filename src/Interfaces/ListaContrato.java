/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Clientes;
import Entidades.ContratoAlquiler;
import Entidades.Reserva;
import Entidades.Vehiculos;
import Excepciones.ContratoExcepciones.ClienteNoRegistradoExcepcion;
import Excepciones.ContratoExcepciones.ContratoFinalizadoExcepcion;
import Excepciones.ContratoExcepciones.ContratoNoEncontradoExcepcion;
import Excepciones.ContratoExcepciones.FechaInvalidaExcepcion;
import Excepciones.ContratoExcepciones.VehiculoNoDisponibleExcepcion;
import Excepciones.ContratoExcepciones.VehiculoNoRegistradoExcepcion;
import java.time.LocalDate;
import java.util.List;

public interface ListaContrato {

    ContratoAlquiler crearContratoConReserva(Reserva reserva, double tarifaDiaria) 
        throws ClienteNoRegistradoExcepcion, VehiculoNoDisponibleExcepcion, VehiculoNoRegistradoExcepcion, FechaInvalidaExcepcion;
    
    ContratoAlquiler crearContratoSinReserva(Clientes cliente, Vehiculos vehiculo, 
        LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria) 
        throws ClienteNoRegistradoExcepcion, VehiculoNoDisponibleExcepcion, VehiculoNoRegistradoExcepcion, FechaInvalidaExcepcion;

    public void finalizarContrato(int contratoID) throws ContratoNoEncontradoExcepcion, ContratoFinalizadoExcepcion;
    
    public void cancelarContrato(int contratoID) throws ContratoFinalizadoExcepcion, ContratoNoEncontradoExcepcion;
    
    ContratoAlquiler buscarContratoPorId(int ContratoID) throws ContratoNoEncontradoExcepcion;
    
    List<ContratoAlquiler> buscarPorCliente(String cedulaCliente) throws ClienteNoRegistradoExcepcion;
    
    List<ContratoAlquiler> buscarPorVehiculo(String placaVehiculo) throws VehiculoNoDisponibleExcepcion, VehiculoNoRegistradoExcepcion;
    
    List<ContratoAlquiler> buscarPorContratoActivo();
    
    List<ContratoAlquiler> buscarPorContratoVencido();
    
    public boolean existeContratoActivo(String placa, LocalDate fechaInicio, LocalDate fechaFinal);
    
}
