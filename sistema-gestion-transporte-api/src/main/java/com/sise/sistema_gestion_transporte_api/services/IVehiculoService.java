package com.sise.sistema_gestion_transporte_api.services;

import java.util.List;

import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;

public interface IVehiculoService {
    List<Vehiculo> listarVehiculos() throws Exception;
    Vehiculo obtenerVehiculo(Integer idVehiculo) throws Exception;
    Vehiculo insertarVehiculo(Vehiculo vehiculo) throws Exception;
    Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws Exception;
    void darBajaVehiculo(Integer idVehiculo) throws Exception;   

}
