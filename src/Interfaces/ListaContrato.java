/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 *
 * @author je110
 */
public interface ListaContrato <T> {
    
    public boolean crear(T t);
    
    public boolean finalizar(T t);
    
    public T Buscar(Object id);
    
    public void cancelar();
    
}
