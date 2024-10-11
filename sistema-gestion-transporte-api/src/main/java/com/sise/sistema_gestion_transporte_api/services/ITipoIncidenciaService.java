package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.TipoIncidencia;

public interface ITipoIncidenciaService {
    Page<TipoIncidencia> listarTiposIncidencia(Pageable pageable) throws Exception;
    TipoIncidencia obtenerTipoIncidencia(Integer idTipoIncidencia) throws Exception;
    TipoIncidencia insertarTipoIncidencia(TipoIncidencia tipoIncidencia) throws Exception;
    TipoIncidencia actualizarTipoIncidencia(TipoIncidencia tipoIncidencia) throws Exception;
    void darBajaTipoIncidencia(Integer idTipoIncidencia) throws Exception;

}
