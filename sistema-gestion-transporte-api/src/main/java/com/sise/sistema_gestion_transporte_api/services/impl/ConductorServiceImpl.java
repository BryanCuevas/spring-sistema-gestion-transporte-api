package com.sise.sistema_gestion_transporte_api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;
import com.sise.sistema_gestion_transporte_api.services.IConductorService;

@Service
public class ConductorServiceImpl implements IConductorService {

    @Override
    public List<Conductor> listarConductores() throws Exception {
        return null;
    }

    @Override
    public Conductor obtenerConductor(Integer idCondutor) throws Exception {
        return null;
    }

    @Override
    public Conductor insertarConductor(Conductor conductor) throws Exception {
        return null;
    }

    @Override
    public Conductor actualizarConductor(Conductor conductor) throws Exception {
        return null;
    }

    @Override
    public void darBajaConductor(Integer idCondutor) throws Exception {

    }

}
