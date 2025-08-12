/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import Validaciones.ValidarClientes;

/**
 *
 * @author samue
 */
public class Clientes extends Persona {
    
    private String licenciaconducir;

    public String getLicenciacondudicir() {
        return licenciaconducir;
    }
    
    public boolean validarLicencia(){
        return ValidarClientes.validarLicencia(licenciaconducir);
    }

    public Clientes(String licenciaconducir, String cedula, String nombre, LocalDate fechaNacimiento, String telefono, String correo) {
        super(cedula, nombre, fechaNacimiento, telefono, correo);
       if(ValidarClientes.validarLicencia(licenciaconducir));
        this.licenciaconducir = licenciaconducir;
    }

    
    
    
}
