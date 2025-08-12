/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

/**
 *
 * @author je110
 */
public interface ListaGeneral <T, ID> {
    
    public boolean agregar(T t);
    
    public boolean eliminar(ID id);
    
    public T buscar(ID id);
    
    public void actualizar(ID id, T t);
    
}
