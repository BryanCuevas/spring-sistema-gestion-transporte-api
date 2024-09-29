package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;

public interface IVehiculoService {
    Page<Vehiculo> listarVehiculos(Pageable pageable) throws Exception;
    Vehiculo obtenerVehiculo(Integer idVehiculo) throws Exception;
    Vehiculo insertarVehiculo(Vehiculo vehiculo) throws Exception;
    Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws Exception;
    void darBajaVehiculo(Integer idVehiculo) throws Exception;   

}
