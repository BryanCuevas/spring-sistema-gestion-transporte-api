package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Carga;
import com.sise.sistema_gestion_transporte_api.repositories.ICargaRepository;
import com.sise.sistema_gestion_transporte_api.services.ICargaService;

@Service
public class CargaServiceImpl implements ICargaService {
    @Autowired
    ICargaRepository cargaRepository;

    @Override
    public Page<Carga> listarCargas(Pageable pageable) throws Exception {
        return cargaRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public Carga obtenerCarga(Integer idCarga) throws Exception {
        return cargaRepository.findOneByIdCargaAndEstadoAuditoria(idCarga, "1");
    }

    @Override
    public Carga insertarCarga(Carga carga) throws Exception {
        return cargaRepository.save(carga);
    }

    @Override
    public Carga actualizarCarga(Carga carga) throws Exception {
        return cargaRepository.save(carga);
    }

    @Override
    public void darBajaCarga(Integer idCarga) throws Exception {
        cargaRepository.darBajaCarga(idCarga);
    }

}
