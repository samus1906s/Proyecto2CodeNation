/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

//SE USA SERIALIZABLE PARA GUARDAR LOS DATOS REALIZADOS EN EL PROGRAMA, Y SIMULAR UNA APLICACION WEB
import Excepciones.VehiculoExceptions.AñoIncorrectoException;
import Excepciones.VehiculoExceptions.TransicionEstadoNoPermitidoException;
import Excepciones.VehiculoExceptions.EstadoInvalidoException;
import Excepciones.VehiculoExceptions.campoVacioException;
import Excepciones.VehiculoExceptions.PlacaInvalidaException;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author je110
 */
public class Vehiculos implements Serializable {
    //La siguiente regla es solo para esta identidad porque la clase implementa serializable, esto hace que java al cargar el programa verifique si sigue siendo compatible y no ha cambiado.
     private static final long serialVersionUID = 1L;
    //Esto crea un patrón de expresión regular para validar la placa.
    private static final Pattern PLACA_REGEX = Pattern.compile("^[A-Z0-9-]{5,10}$");

   
    private final String placa;                
    private String marca;
    private String modelo;                     
    private int anio;
    private TipoVehiculo tipo;                
    private EstadoVehiculos estado;            

  
    public Vehiculos(String placa, String marca, String modelo, int anio,
        TipoVehiculo tipo, EstadoVehiculos estadoInicial) throws PlacaInvalidaException, campoVacioException, AñoIncorrectoException, EstadoInvalidoException {

        this.placa  = validarPlaca(placa);
        this.marca  = validarObligatorio("marca", marca);
        this.modelo = validarObligatorio("modelo", modelo);
        this.anio   = validarAnio(anio);
        this.tipo   = validarTipo(tipo);
        this.estado = (estadoInicial == null) ? EstadoVehiculos.DISPONIBLE : validarEstado(estadoInicial);
    }

    //  Metodos GET
    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public TipoVehiculo getTipo() { return tipo; }
    public EstadoVehiculos getEstado() { return estado; }

    // mETODOS SET
    public void setModelo(String modelo) throws campoVacioException {
        this.modelo = validarObligatorio("modelo", modelo);
    }

    public void setTipo(TipoVehiculo tipo) throws campoVacioException {
        this.tipo = validarTipo(tipo);
    }

    public void setEstado(EstadoVehiculos nuevoEstado) throws TransicionEstadoNoPermitidoException, EstadoInvalidoException {
        EstadoVehiculos destino = validarEstado(nuevoEstado);
        if (!puedeCambiarAEstado(this.estado, destino)) {
            throw new TransicionEstadoNoPermitidoException("Transición no permitida: " + this.estado + " → " + destino);
        }
        this.estado = destino;
    }

   
    public boolean isDisponible() { return estado == EstadoVehiculos.DISPONIBLE; }

    
    public void iniciarAlquiler() throws TransicionEstadoNoPermitidoException {
        if (estado != EstadoVehiculos.DISPONIBLE) {
            throw new TransicionEstadoNoPermitidoException("El vehículo no está disponible para alquilar.");
        }
        estado = EstadoVehiculos.EN_ALQUILER;
    }

    
    public void finalizarAlquiler() throws TransicionEstadoNoPermitidoException {
        if (estado != EstadoVehiculos.EN_ALQUILER) {
            throw new TransicionEstadoNoPermitidoException("Solo un vehículo en alquiler puede finalizarse.");
        }
        estado = EstadoVehiculos.DISPONIBLE;
    }

  
    public void enviarAMantenimiento() throws TransicionEstadoNoPermitidoException {
        if (estado == EstadoVehiculos.EN_ALQUILER) {
            throw new TransicionEstadoNoPermitidoException("No se puede enviar a mantenimiento mientras está en alquiler.");
        }
        estado = EstadoVehiculos.EN_MANTENIMIENTO;
    }

   
    public void salirDeMantenimiento() throws TransicionEstadoNoPermitidoException {
        if (estado != EstadoVehiculos.EN_MANTENIMIENTO) {
            throw new TransicionEstadoNoPermitidoException("El vehículo no está en mantenimiento.");
        }
        estado = EstadoVehiculos.DISPONIBLE;
    }

   
    private static String validarPlaca(String placa) throws PlacaInvalidaException, campoVacioException {
        String p = validarObligatorio("placa", placa).toUpperCase().trim();
        if (!PLACA_REGEX.matcher(p).matches()) {
            throw new PlacaInvalidaException("Formato de placa inválido: " + p);
        }
        return p;
    }

    private static String validarObligatorio(String campo, String valor) throws campoVacioException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new campoVacioException("El campo " + campo + " es obligatorio.");
        }
        return valor.trim();
    }

    private static int validarAnio(int anio) throws AñoIncorrectoException {
        int actual = Year.now().getValue();
        if (anio > actual) {
            throw new AñoIncorrectoException("El año no puede ser mayor al año actual.");
        }
        if (actual - anio > 20) {
            throw new AñoIncorrectoException("El vehículo no puede tener más de 20 años de antigüedad.");
        }
        return anio;
    }

    private static TipoVehiculo validarTipo(TipoVehiculo tipo) throws campoVacioException {
        if (tipo == null) {
            throw new campoVacioException("El tipo de vehículo es obligatorio.");
        }
        
        return tipo;
    }

    private static EstadoVehiculos validarEstado(EstadoVehiculos estado) throws EstadoInvalidoException {
        if (estado == null) {
            throw new EstadoInvalidoException("El estado no puede ser nulo.");
        }
       
        return estado;
    }

    
    private static boolean puedeCambiarAEstado(EstadoVehiculos actual, EstadoVehiculos destino) {
        if (actual == null || destino == null) return false;
        switch (actual) {
            case DISPONIBLE:
                return destino == EstadoVehiculos.EN_ALQUILER || destino == EstadoVehiculos.EN_MANTENIMIENTO;
            case EN_ALQUILER:
                return destino == EstadoVehiculos.DISPONIBLE;
            case EN_MANTENIMIENTO:
                return destino == EstadoVehiculos.DISPONIBLE;
            default:
                return false;
        }
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehiculos)) return false;
        Vehiculos vehiculo = (Vehiculos) o;
        return Objects.equals(placa, vehiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anio=" + anio +
                ", tipo=" + tipo +
                ", estado=" + estado +
                '}';
    }
    
}
