package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;

public interface IConductorService {
    Page<Conductor> listarConductores(Pageable pageable) throws Exception;
    Conductor obtenerConductor(Integer idCondutor) throws Exception;
    Conductor insertarConductor(Conductor conductor) throws Exception;
    Conductor actualizarConductor(Conductor conductor) throws Exception;
    void darBajaConductor(Integer idCondutor) throws Exception;  

}
