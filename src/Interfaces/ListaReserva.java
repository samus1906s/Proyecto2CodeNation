/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 *
 * @author Eduard Salas Murillo
 */
public interface ListaReserva <R> {
   public boolean modificar(R r);
    
    public boolean cancelar(R r);
    
    public R buscar(Object id);
    
    public void confirmar();
    
}
