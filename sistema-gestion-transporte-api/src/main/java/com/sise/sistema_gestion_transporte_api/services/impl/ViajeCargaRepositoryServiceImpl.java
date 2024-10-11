package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.ViajeCarga;
import com.sise.sistema_gestion_transporte_api.repositories.IViajeCargaRepository;
import com.sise.sistema_gestion_transporte_api.services.IViajeCargaService;

@Service
public class ViajeCargaRepositoryServiceImpl implements IViajeCargaService {
    @Autowired
    IViajeCargaRepository viajeCargaRepository;

    @Override
    public Page<ViajeCarga> listarViajesCargas(Pageable pageable) throws Exception {
        return viajeCargaRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public ViajeCarga obtenerViajeCarga(Integer idViajeCarga) throws Exception {
        return viajeCargaRepository.findOneByIdViajeCargaAndEstadoAuditoria(idViajeCarga,"1");
    }

    @Override
    public ViajeCarga insertarViajeCarga(ViajeCarga viajeCarga) throws Exception {
        return viajeCargaRepository.save(viajeCarga);
    }

    @Override
    public ViajeCarga actualizarViajeCarga(ViajeCarga viajeCarga) throws Exception {
        return viajeCargaRepository.save(viajeCarga);
    }

    @Override
    public void darBajaViajeCarga(Integer idViajeCarga) throws Exception {
        viajeCargaRepository.darBajaViajeCarga(idViajeCarga);
    }
}
