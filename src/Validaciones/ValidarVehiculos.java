/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;
import Entidades.EstadoVehiculos;
import Entidades.TipoVehiculo;
import Entidades.Vehiculos;
import Excepciones.VehiculoExceptions.AñoIncorrectoExcepcion;
import Excepciones.VehiculoExceptions.TransicionEstadoNoPermitidoExcepcion;
import Excepciones.VehiculoExceptions.EstadoInvalidoExcepcion;
import Excepciones.VehiculoExceptions.CampoVacioExcepcion;
import Excepciones.VehiculoExceptions.PlacaInvalidaExcepcion;


import java.time.Year;
import java.util.Map;
import java.util.regex.Pattern;
/**
 *
 * @author Brandon Valdelomar
 */
public class ValidarVehiculos {
    private static final Pattern PLACA_REGEX = Pattern.compile("^[A-Z0-9-]{5,10}$");
    
    public static boolean placaValida(String placa) {
        if (placa == null) return false;
        String p = placa.trim().toUpperCase();
        return PLACA_REGEX.matcher(p).matches();
    }

    public static boolean anioValido(int anio) {
        int actual = Year.now().getValue();
        if (anio > actual) return false;           
        return (actual - anio) <= 20;              
    }

    public static boolean tipoValido(TipoVehiculo tipo) {
        return tipo == TipoVehiculo.SEDAN ||
               tipo == TipoVehiculo.SUV   ||
               tipo == TipoVehiculo.PICK_UP;
    }

    public static boolean estadoValido(EstadoVehiculos estado) {
        return estado == EstadoVehiculos.DISPONIBLE ||
               estado == EstadoVehiculos.EN_ALQUILER ||
               estado == EstadoVehiculos.EN_MANTENIMIENTO;
    }

    public static boolean placaDuplicada(String placa, Map<String, Vehiculos> repo) {
        if (placa == null || repo == null) return false;
        return repo.containsKey(placa.trim().toUpperCase());
    }
   
    public static boolean puedeAgregar(Vehiculos v, Map<String, Vehiculos> repo) {
        if (v == null || repo == null) return false;
        String placa = v.getPlaca();
        return placaValida(placa)
                && !placaDuplicada(placa, repo)
                && notEmpty(v.getMarca())
                && notEmpty(v.getModelo())
                && anioValido(v.getAnio())
                && tipoValido(v.getTipo())
                && estadoValido(v.getEstado());
    }

    public static boolean puedeActualizarModelo(String nuevoModelo) {
        return notEmpty(nuevoModelo);
    }

    public static boolean puedeActualizarTipo(TipoVehiculo nuevoTipo) {
        return tipoValido(nuevoTipo);
    }

    public static boolean puedeCambiarAEstado(EstadoVehiculos actual, EstadoVehiculos destino) {
        if (!estadoValido(actual) || !estadoValido(destino)) return false;
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

    public static boolean puedeActualizarEstado(EstadoVehiculos actual, EstadoVehiculos nuevo) {
        return puedeCambiarAEstado(actual, nuevo);
    }

    public static boolean puedeEliminar(Vehiculos v) {
        return v != null && v.getEstado() != EstadoVehiculos.EN_ALQUILER;
    }
   
    public static void assertPuedeAgregar(Vehiculos v, Map<String, Vehiculos> repo) 
            throws PlacaInvalidaExcepcion, CampoVacioExcepcion, AñoIncorrectoExcepcion, EstadoInvalidoExcepcion {
        if (v == null || repo == null) {
            throw new IllegalArgumentException();
        }
        String placa = safeUpper(v.getPlaca());
        if (!placaValida(placa)) throw new PlacaInvalidaExcepcion();
        if (placaDuplicada(placa, repo)) throw new PlacaInvalidaExcepcion();
        if (!notEmpty(v.getMarca())) throw new CampoVacioExcepcion();
        if (!notEmpty(v.getModelo())) throw new CampoVacioExcepcion();
        if (!anioValido(v.getAnio())) throw new AñoIncorrectoExcepcion();
        if (!tipoValido(v.getTipo())) throw new CampoVacioExcepcion();
        if (!estadoValido(v.getEstado())) throw new EstadoInvalidoExcepcion();
    }

    public static void assertPuedeActualizarModelo(String nuevoModelo) throws CampoVacioExcepcion {
        if (!notEmpty(nuevoModelo)) throw new CampoVacioExcepcion();
    }

    public static void assertPuedeActualizarTipo(TipoVehiculo nuevoTipo) throws CampoVacioExcepcion {
        if (!tipoValido(nuevoTipo)) throw new CampoVacioExcepcion();
    }

    public static void assertPuedeActualizarEstado(EstadoVehiculos actual, EstadoVehiculos nuevo)
        throws EstadoInvalidoExcepcion, TransicionEstadoNoPermitidoExcepcion {
        if (!estadoValido(actual) || !estadoValido(nuevo)) {
            throw new EstadoInvalidoExcepcion();
        }
        if (!puedeCambiarAEstado(actual, nuevo)) {
            throw new TransicionEstadoNoPermitidoExcepcion();
        }
    }

    public static void assertPuedeEliminar(Vehiculos v) throws EstadoInvalidoExcepcion {
        if (!puedeEliminar(v)) throw new EstadoInvalidoExcepcion();
    }
   
    private static boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private static String safeUpper(String s) {
        return (s == null) ? null : s.trim().toUpperCase();
    }
}
