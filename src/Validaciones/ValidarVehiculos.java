/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;
import Entidades.EstadoVehiculos;
import Entidades.TipoVehiculo;
import Entidades.Vehiculos;
import Excepciones.VehiculoExceptions.AñoIncorrectoException;
import Excepciones.VehiculoExceptions.TransicionEstadoNoPermitidoException;
import Excepciones.VehiculoExceptions.EstadoInvalidoException;
import Excepciones.VehiculoExceptions.campoVacioException;
import Excepciones.VehiculoExceptions.PlacaInvalidaException;


import java.time.Year;
import java.util.Map;
import java.util.regex.Pattern;
/**
 *
 * @author je110
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
        // No eliminar si está en alquiler
        return v != null && v.getEstado() != EstadoVehiculos.EN_ALQUILER;
    }

   
    public static void assertPuedeAgregar(Vehiculos v, Map<String, Vehiculos> repo) throws PlacaInvalidaException, campoVacioException, AñoIncorrectoException, EstadoInvalidoException {
        if (v == null || repo == null) {
            throw new IllegalArgumentException("Vehículo o repositorio nulo.");
        }
        String placa = safeUpper(v.getPlaca());
        if (!placaValida(placa)) throw new PlacaInvalidaException("Formato de placa inválido.");
        if (placaDuplicada(placa, repo)) throw new PlacaInvalidaException("Placa duplicada: " + placa);
        if (!notEmpty(v.getMarca())) throw new campoVacioException("La marca es obligatoria.");
        if (!notEmpty(v.getModelo())) throw new campoVacioException("El modelo es obligatorio.");
        if (!anioValido(v.getAnio())) throw new AñoIncorrectoException("Año inválido o antigüedad > 20.");
        if (!tipoValido(v.getTipo())) throw new campoVacioException("Tipo de vehículo inválido.");
        if (!estadoValido(v.getEstado())) throw new EstadoInvalidoException("Estado de vehículo inválido.");
    }

    public static void assertPuedeActualizarModelo(String nuevoModelo) throws campoVacioException {
        if (!notEmpty(nuevoModelo)) throw new campoVacioException("El modelo es obligatorio.");
    }

    public static void assertPuedeActualizarTipo(TipoVehiculo nuevoTipo) throws campoVacioException {
        if (!tipoValido(nuevoTipo)) throw new campoVacioException("Tipo de vehículo inválido.");
    }

    public static void assertPuedeActualizarEstado(EstadoVehiculos actual, EstadoVehiculos nuevo)
        throws EstadoInvalidoException, TransicionEstadoNoPermitidoException {
    if (!estadoValido(actual) || !estadoValido(nuevo)) {
        throw new EstadoInvalidoException("Estado inválido.");
    }
    if (!puedeCambiarAEstado(actual, nuevo)) {
        throw new TransicionEstadoNoPermitidoException("Transición de estado no permitida.");
    }
}

    public static void assertPuedeEliminar(Vehiculos v) throws EstadoInvalidoException {
        if (!puedeEliminar(v)) throw new EstadoInvalidoException("No se puede eliminar un vehículo en alquiler.");
    }

   
    private static boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private static String safeUpper(String s) {
        return (s == null) ? null : s.trim().toUpperCase();
    }
}
