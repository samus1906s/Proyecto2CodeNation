/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.ContratoAlquiler;
import Entidades.EstadoContrato;

/**
 *
 * @author je110
 */
public interface ListaContrato {
    
ContratoAlquiler crearContratoConReserva(double tarifaDiaria);
ContratoAlquiler crearContratoSinReserva();

public void finalizarContrato(int contratoID);
public void cancelarContrato(int contratoID);

}
