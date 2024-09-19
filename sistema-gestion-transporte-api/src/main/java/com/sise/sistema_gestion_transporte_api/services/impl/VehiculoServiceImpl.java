package com.sise.sistema_gestion_transporte_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;
import com.sise.sistema_gestion_transporte_api.repositories.IVehiculoRepository;
import com.sise.sistema_gestion_transporte_api.services.IVehiculoService;

@Service
public class VehiculoServiceImpl implements IVehiculoService {
    @Autowired
    IVehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listarVehiculos() throws Exception {
        return vehiculoRepository.findByEstadoAuditoria("1");
    }

    @Override
    public Vehiculo obtenerVehiculo(Integer idVehiculo) throws Exception {
        return vehiculoRepository.findOneByIdVehiculoAndEstadoAuditoria(idVehiculo,"1");
    }

    @Override
    public Vehiculo insertarVehiculo(Vehiculo vehiculo) throws Exception {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo actualizarVehiculo(Vehiculo vehiculo) throws Exception {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void darBajaVehiculo(Integer idVehiculo) throws Exception {
        vehiculoRepository.darBajaVehiculo(idVehiculo);
    }

}
