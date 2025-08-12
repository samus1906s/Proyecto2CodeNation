/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;
import Entidades.PuestoEmpleado;

/**
 *
 * @author samue
 */
public class ValidarEmpleados {
    private static final double salarioMinimo = 300000;
    private static final double salarioMaximo = 500000;
    
    public static boolean validarPuesto(String cargo){
        return PuestoEmpleado.buscarPorNombre(cargo) != null;
    }
    
    public static boolean validarSalario(double saldo){
        return saldo >= salarioMinimo && saldo <= salarioMaximo;
    }
    
    public static double obtenerSalarioMinimo() {
        return salarioMinimo;
    }
    
    public static double obtenerSalarioMaximo() {
        return salarioMaximo;
    }

}
