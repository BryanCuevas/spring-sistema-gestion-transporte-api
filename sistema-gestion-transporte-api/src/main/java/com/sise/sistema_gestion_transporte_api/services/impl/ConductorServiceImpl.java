package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;
import com.sise.sistema_gestion_transporte_api.repositories.IConductorRepository;
import com.sise.sistema_gestion_transporte_api.services.IConductorService;

@Service
public class ConductorServiceImpl implements IConductorService {
    @Autowired
    IConductorRepository conductorRepository;

    @Override
    public Page<Conductor> listarConductores(Pageable pageable) throws Exception {
        return conductorRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public Conductor obtenerConductor(Integer idCondutor) throws Exception {
        return conductorRepository.findOneByIdConductorAndEstadoAuditoria(idCondutor, "1");
    }

    @Override
    public Conductor insertarConductor(Conductor conductor) throws Exception {
        return conductorRepository.save(conductor);
    }

    @Override
    public Conductor actualizarConductor(Conductor conductor) throws Exception {
        return conductorRepository.save(conductor);
    }

    @Override
    public void darBajaConductor(Integer idConductor) throws Exception {
        conductorRepository.darBajaConductor(idConductor);
    }

}
