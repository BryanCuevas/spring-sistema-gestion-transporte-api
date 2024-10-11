package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.TipoIncidencia;
import com.sise.sistema_gestion_transporte_api.repositories.ITipoIncidenciaRepository;
import com.sise.sistema_gestion_transporte_api.services.ITipoIncidenciaService;

@Service
public class TipoIncidenciaServiceImpl implements ITipoIncidenciaService {
    @Autowired
    ITipoIncidenciaRepository tipoIncidenciaRepository;

    @Override
    public Page<TipoIncidencia> listarTiposIncidencia(Pageable pageable) throws Exception {
        return tipoIncidenciaRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public TipoIncidencia obtenerTipoIncidencia(Integer idTipoIncidencia) throws Exception {
        return tipoIncidenciaRepository.findOneByIdTipoIncidenciaAndEstadoAuditoria(idTipoIncidencia,"1");
    }

    @Override
    public TipoIncidencia insertarTipoIncidencia(TipoIncidencia tipoIncidencia) throws Exception {
        return tipoIncidenciaRepository.save(tipoIncidencia);
    }

    @Override
    public TipoIncidencia actualizarTipoIncidencia(TipoIncidencia tipoIncidencia) throws Exception {
        return tipoIncidenciaRepository.save(tipoIncidencia);
    }

    @Override
    public void darBajaTipoIncidencia(Integer idTipoIncidencia) throws Exception {
        tipoIncidenciaRepository.darBajaTipoIncidencia(idTipoIncidencia);
    }

}
