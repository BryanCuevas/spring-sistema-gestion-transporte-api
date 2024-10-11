package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Incidencia;
import com.sise.sistema_gestion_transporte_api.repositories.IIncidenciaRepository;
import com.sise.sistema_gestion_transporte_api.services.IIncidenciaService;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService {
    @Autowired
    IIncidenciaRepository incidenciaRepository;

    @Override
    public Page<Incidencia> listarIncidencias(Pageable pageable) throws Exception {
        return incidenciaRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public Incidencia obtenerIncidencia(Integer idIncidencia) throws Exception {
        return incidenciaRepository.findOneByIdIncidenciaAndEstadoAuditoria(idIncidencia,"1");
    }

    @Override
    public Incidencia insertarIncidencia(Incidencia incidencia) throws Exception {
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public Incidencia actualizarIncidencia(Incidencia incidencia) throws Exception {
        return incidenciaRepository.save(incidencia);
    }

    @Override
    public void darBajaIncidencia(Integer idIncidencia) throws Exception {
        incidenciaRepository.darBajaIncidencia(idIncidencia);
    }
}
