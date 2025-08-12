/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import Validaciones.ValidarEmpleados;

/**
 *
 * @author samue
 */
public class Empleados extends Persona{
    private PuestoEmpleado puesto;
    private double salario;

    public PuestoEmpleado getPuesto() {
        return puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setPuesto(PuestoEmpleado puesto) {
        this.puesto = puesto;
    }

    public void setSalario(double salario) {
        if(ValidarEmpleados.validarSalario(salario))
           this.salario = salario;
    }
    

    public Empleados( String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
        if(ValidarEmpleados.validarPuesto(puesto.getFuncion())){
            this.puesto = puesto;
        }else{
            this.puesto = PuestoEmpleado.SECRETARIO;
        }
        if(ValidarEmpleados.validarSalario(salario)){
          this.salario = salario;
        }else
          this.salario = 0;
    }
    
    
    
    
    
}
