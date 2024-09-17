package com.sise.sistema_gestion_transporte_api.services;

import java.util.List;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;

public interface IConductorService {
    List<Conductor> listarConductores() throws Exception;
    Conductor obtenerConductor(Integer idCondutor) throws Exception;
    Conductor insertarConductor(Conductor conductor) throws Exception;
    Conductor actualizarConductor(Conductor conductor) throws Exception;
    void darBajaConductor(Integer idCondutor) throws Exception;  

}
