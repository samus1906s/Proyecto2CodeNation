/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

//SE USA SERIALIZABLE PARA GUARDAR LOS DATOS REALIZADOS EN EL PROGRAMA, Y SIMULAR UNA APLICACION WEB
import Excepciones.VehiculoExcepciones.AñoIncorrectoExcepcion;
import Excepciones.VehiculoExcepciones.TransicionEstadoNoPermitidoExcepcion;
import Excepciones.VehiculoExcepciones.EstadoInvalidoExcepcion;
import Excepciones.VehiculoExcepciones.CampoVacioExcepcion;
import Excepciones.VehiculoExcepciones.PlacaInvalidaExcepcion;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author Brandon Valdelomar
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
         TipoVehiculo tipo, EstadoVehiculos estadoInicial) throws PlacaInvalidaExcepcion, CampoVacioExcepcion, AñoIncorrectoExcepcion, EstadoInvalidoExcepcion {
        this.placa  = validarPlaca(placa);
        this.marca  = validarObligatorio(marca);
        this.modelo = validarObligatorio(modelo);
        this.anio   = validarAnio(anio);
        this.tipo   = validarTipo(tipo);
        this.estado = (estadoInicial == null) ? EstadoVehiculos.DISPONIBLE : validarEstado(estadoInicial);
    }

    public String getPlaca() { return placa; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public TipoVehiculo getTipo() { return tipo; }
    public EstadoVehiculos getEstado() { return estado; }

    public void setModelo(String modelo) throws CampoVacioExcepcion {
        this.modelo = validarObligatorio(modelo);
    }

    public void setTipo(TipoVehiculo tipo) throws CampoVacioExcepcion {
        this.tipo = validarTipo(tipo);
    }

    public void setEstado(EstadoVehiculos nuevoEstado) throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        EstadoVehiculos destino = validarEstado(nuevoEstado);
        if (!puedeCambiarAEstado(this.estado, destino)) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        this.estado = destino;
    }
   
    public boolean isDisponible() { return estado == EstadoVehiculos.DISPONIBLE; }
    
    public void iniciarAlquiler() throws TransicionEstadoNoPermitidoExcepcion {
        if (estado != EstadoVehiculos.DISPONIBLE) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        estado = EstadoVehiculos.EN_ALQUILER;
    }
    
    public void finalizarAlquiler() throws TransicionEstadoNoPermitidoExcepcion {
        if (estado != EstadoVehiculos.EN_ALQUILER) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        estado = EstadoVehiculos.DISPONIBLE;
    }
  
    public void enviarAMantenimiento() throws TransicionEstadoNoPermitidoExcepcion {
        if (estado == EstadoVehiculos.EN_ALQUILER) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        estado = EstadoVehiculos.EN_MANTENIMIENTO;
    }
   
    public void salirDeMantenimiento() throws TransicionEstadoNoPermitidoExcepcion {
        if (estado != EstadoVehiculos.EN_MANTENIMIENTO) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
        estado = EstadoVehiculos.DISPONIBLE;
    }
   
    private static String validarPlaca(String placa) throws PlacaInvalidaExcepcion, CampoVacioExcepcion {
        String p = validarObligatorio(placa).toUpperCase().trim();
        if (!PLACA_REGEX.matcher(p).matches()) {
            throw new PlacaInvalidaExcepcion();
        }
        return p;
    }

    private static String validarObligatorio(String valor) throws CampoVacioExcepcion {
        if (valor == null || valor.trim().isEmpty()) {
            throw new CampoVacioExcepcion();
        }
        return valor.trim();
    }

    private static int validarAnio(int anio) throws AñoIncorrectoExcepcion {
        int actual = Year.now().getValue();
        if (anio > actual) {
            throw new AñoIncorrectoExcepcion();
        }
        if (actual - anio > 20) {
            throw new AñoIncorrectoExcepcion();
        }
        return anio;
    }

    private static TipoVehiculo validarTipo(TipoVehiculo tipo) throws CampoVacioExcepcion {
        if (tipo == null) {
            throw new CampoVacioExcepcion();
        }
        return tipo;
    }

    private static EstadoVehiculos validarEstado(EstadoVehiculos estado) throws EstadoInvalidoExcepcion {
        if (estado == null) {
            throw new EstadoInvalidoExcepcion();
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
}
