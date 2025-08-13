/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Registros;
import Validaciones.ValidarVehiculos;
import Entidades.Vehiculos;
import Entidades.EstadoVehiculos;
import Entidades.TipoVehiculo;
import Excepciones.VehiculoExcepciones.EstadoInvalidoExcepcion;
import Excepciones.VehiculoExcepciones.TransicionEstadoNoPermitidoExcepcion;
import Excepciones.VehiculoExcepciones.CampoVacioExcepcion;
import Interfaces.ListaGeneral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Valdelomar
 */
public class VehiculosHashMap implements ListaGeneral<Vehiculos, String> {
    
    private final Map<String, Vehiculos> data = new HashMap<>();

    @Override
    public boolean agregar(Vehiculos v) {
        if (v == null) return false;
        if (!ValidarVehiculos.puedeAgregar(v, data)) return false;
        String k = norm(v.getPlaca());
        if (data.containsKey(k)) return false;
        data.put(k, v);
        return true;
    }

    @Override
    public boolean eliminar(String id) {
        String k = norm(id);
        if (k == null) return false;
        Vehiculos v = data.get(k);
        if (v == null) return false;
        if (!ValidarVehiculos.puedeEliminar(v)) return false;
        data.remove(k);
        return true;
    }

    @Override
    public Vehiculos buscar(String id) {
        String k = norm(id);
        if (k == null) return null;
        return data.get(k);
    }

    @Override
    public void actualizar(String id, Vehiculos t) {
        String k = norm(id);
        if (k == null || t == null) return;
        if (!data.containsKey(k)) return;
        data.put(k, t);
    }

    public Vehiculos buscarPorPlaca(String placa) {
        String k = norm(placa);
        if (k == null) return null;
        return data.get(k);
    }

    public List<Vehiculos> buscarPorTipo(TipoVehiculo tipo) {
        List<Vehiculos> out = new ArrayList<>();
        if (tipo == null) return out;
        for (Vehiculos v : data.values()) {
            if (tipo.equals(v.getTipo())) out.add(v);
        }
        return out;
    }

    public List<Vehiculos> listarTodos() {
        return new ArrayList<>(data.values());
    }

    public int total() {
        return data.size();
    }
    
    public boolean actualizarModelo(String placa, String nuevoModelo) throws CampoVacioExcepcion {
        Vehiculos v = buscarPorPlaca(placa);
        if (v == null) return false;
        if (!ValidarVehiculos.puedeActualizarModelo(nuevoModelo)) return false;
        v.setModelo(nuevoModelo.trim());
        return true;
    }

    public boolean actualizarTipo(String placa, TipoVehiculo nuevoTipo) throws CampoVacioExcepcion {
        Vehiculos v = buscarPorPlaca(placa);
        if (v == null) return false;
        if (!ValidarVehiculos.puedeActualizarTipo(nuevoTipo)) return false;
        v.setTipo(nuevoTipo);
        return true;
    }

    public boolean actualizarEstado(String placa, EstadoVehiculos nuevoEstado)
            throws TransicionEstadoNoPermitidoExcepcion, EstadoInvalidoExcepcion {
        Vehiculos v = buscarPorPlaca(placa);
        if (v == null) return false;
        if (!ValidarVehiculos.puedeActualizarEstado(v.getEstado(), nuevoEstado)) return false;
        v.setEstado(nuevoEstado);
        return true;
    }

    private static String norm(String placa) {
        return (placa == null) ? null : placa.trim().toUpperCase();
    }
}
