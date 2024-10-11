package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Incidencia;

public interface IIncidenciaService {
    Page<Incidencia> listarIncidencias(Pageable pageable) throws Exception;
    Incidencia obtenerIncidencia(Integer idIncidencia) throws Exception;
    Incidencia insertarIncidencia(Incidencia incidencia) throws Exception;
    Incidencia actualizarIncidencia(Incidencia incidencia) throws Exception;
    void darBajaIncidencia(Integer idIncidencia) throws Exception;
    
}
