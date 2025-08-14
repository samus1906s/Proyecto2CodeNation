/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Registros;

import Entidades.Clientes;
import Entidades.ContratoAlquiler;
import Entidades.EstadoContrato;
import Entidades.Reserva;
import Entidades.Vehiculos;
import Excepciones.ContratoExcepciones.ClienteNoRegistradoExcepcion;
import Excepciones.ContratoExcepciones.ContratoFinalizadoExcepcion;
import Excepciones.ContratoExcepciones.ContratoNoEncontradoExcepcion;
import Excepciones.ContratoExcepciones.FechaInvalidaExcepcion;
import Excepciones.ContratoExcepciones.VehiculoNoDisponibleExcepcion;
import Excepciones.ContratoExcepciones.VehiculoNoRegistradoExcepcion;
import Interfaces.ListaContrato;
import static Validaciones.ValidacionGeneral.FechaFinPosterior;
import static Validaciones.ValidacionGeneral.FechaInicioValida;
import static Validaciones.ValidacionGeneral.FechasDeRangoValidas;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author je110
 */
public class ContratosHashMap implements ListaContrato {
    HashMap<Integer, ContratoAlquiler> cont;

    public ContratosHashMap() {
        this.cont = new HashMap<>();
    }  

    @Override
    public ContratoAlquiler crearContratoConReserva(Reserva reserva, double tarifaDiaria) throws ClienteNoRegistradoExcepcion, VehiculoNoDisponibleExcepcion, VehiculoNoRegistradoExcepcion, FechaInvalidaExcepcion {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ContratoAlquiler crearContratoSinReserva(Clientes cliente, Vehiculos vehiculo, LocalDate fechaInicial, LocalDate fechaFinal, double tarifaDiaria) throws ClienteNoRegistradoExcepcion, VehiculoNoDisponibleExcepcion, VehiculoNoRegistradoExcepcion, FechaInvalidaExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void finalizarContrato(int contratoID) throws ContratoNoEncontradoExcepcion, ContratoFinalizadoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cancelarContrato(int contratoID) throws ContratoFinalizadoExcepcion, ContratoNoEncontradoExcepcion {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ContratoAlquiler buscarContratoPorId(int ContratoID) throws ContratoNoEncontradoExcepcion {
        if (cont.containsKey(ContratoID)) {
        return cont.get(ContratoID);
        }
        throw new ContratoNoEncontradoExcepcion();
    }

    @Override
    public List<ContratoAlquiler> buscarPorCliente(String cedulaCliente) throws ClienteNoRegistradoExcepcion {
    List<ContratoAlquiler> resultadosCliente = new ArrayList<>();
    
    for (ContratoAlquiler contrato : cont.values()) {
        if (contrato.getCliente().getCedula().equals(cedulaCliente)) {
            resultadosCliente.add(contrato);
        }
    }

    if (resultadosCliente.isEmpty()) {
        throw new ClienteNoRegistradoExcepcion();
    }

    return resultadosCliente;
}


    @Override
public List<ContratoAlquiler> buscarPorVehiculo(String placaVehiculo) throws VehiculoNoDisponibleExcepcion, VehiculoNoRegistradoExcepcion {

    boolean vehiculoRegistrado = false;
    for (ContratoAlquiler contrato : cont.values()) {
        if (contrato.getVehiculo().getPlaca().equals(placaVehiculo)) {
            vehiculoRegistrado = true;
            break;
        }
    }

    if (!vehiculoRegistrado) {
        throw new VehiculoNoRegistradoExcepcion();
    }

    List<ContratoAlquiler> resultadosVehiculo = new ArrayList<>();
    for (ContratoAlquiler contrato : cont.values()) {
        if (contrato.getVehiculo().getPlaca().equals(placaVehiculo)) {
            resultadosVehiculo.add(contrato);
        }
    }

    if (resultadosVehiculo.isEmpty()) {
        throw new VehiculoNoDisponibleExcepcion();
    }

    return resultadosVehiculo;
}

    @Override
    public List<ContratoAlquiler> buscarPorContratoActivo() {
        List<ContratoAlquiler> resultadosActivo = new ArrayList<>();
        
        for (ContratoAlquiler contrato : cont.values()) {
            boolean fechasValidas = FechaInicioValida(contrato.getFechaInicial()) && Validaciones.ValidacionGeneral.FechaFinPosterior(contrato.getFechaInicial(), contrato.getFechaFinal());
            
        if (contrato.getEstadoContrato() == EstadoContrato.ACTIVO || !fechasValidas) {
            resultadosActivo.add(contrato);
        }
      
      }
        return resultadosActivo;
    }

    @Override
    public List<ContratoAlquiler> buscarPorContratoVencido() {
        List<ContratoAlquiler> resultadosVencido = new ArrayList<>();
        
        for (ContratoAlquiler contrato : cont.values()) {
            boolean fechasVencidas = contrato.getFechaFinal().isBefore(LocalDate.now());
            
        if (contrato.getEstadoContrato() == EstadoContrato.ACTIVO || !fechasVencidas) {
            resultadosVencido.add(contrato);
        }
      
      }
        return resultadosVencido;
    }

    @Override
    public boolean existeContratoActivo(String placa, LocalDate fechaInicio, LocalDate fechaFinal) {
        
        for (ContratoAlquiler contrato : cont.values()) {
        
        if (contrato.getVehiculo().getPlaca().equals(placa)) {
            
            boolean fechasValidasRango = FechasDeRangoValidas(contrato.getFechaInicial(), contrato.getFechaFinal(),fechaInicio, fechaFinal);

            if (fechasValidasRango && contrato.getEstadoContrato() == EstadoContrato.ACTIVO) {
                return true; 
            }
        }
    }
    return false; 
    }
    
}
