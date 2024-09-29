package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;
import com.sise.sistema_gestion_transporte_api.repositories.IViajeRepository;
import com.sise.sistema_gestion_transporte_api.services.IViajeService;

@Service
public class ViajeServiceImpl implements IViajeService {
    @Autowired
    IViajeRepository viajeService;

    @Override
    public Page<Viaje> listarViajes(Pageable pageable) throws Exception {
        return viajeService.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public Viaje obtenerViaje(Integer idViaje) throws Exception {
        return viajeService.findOneByIdViajeAndEstadoAuditoria(idViaje,"1");
    }

    @Override
    public Viaje insertarViaje(Viaje viaje) throws Exception {
        return viajeService.save(viaje);
    }

    @Override
    public Viaje actualizarViaje(Viaje viaje) throws Exception {
        return viajeService.save(viaje);
    }

    @Override
    public void darBajaViaje(Integer idViaje) throws Exception {
        viajeService.darBajaViaje(idViaje);
    }

}
