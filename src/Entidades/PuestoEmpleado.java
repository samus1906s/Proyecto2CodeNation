/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Entidades;

/**
 *
 * @author samue
 */
public enum PuestoEmpleado {
    GERENTE("Gerente"),
    CONTADOR("Contador"),
    MECANICO("Mecanico"),
    SECRETARIO("Secretario");
    
    private final String funcion;
    
    PuestoEmpleado(String funcion){
        this.funcion = funcion;
    }

    public String getFuncion() {
        return funcion;
    }
    
    public static PuestoEmpleado buscarPorNombre(String funcion) {
        for (PuestoEmpleado puesto : PuestoEmpleado.values()) {
            if (puesto.getFuncion().equalsIgnoreCase(funcion)) {
                return puesto;
            }
        }
        return null;
    }
}
