package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;
import com.sise.sistema_gestion_transporte_api.repositories.IAlmacenRepository;
import com.sise.sistema_gestion_transporte_api.services.IAlmacenService;

@Service
public class AlmacenServiceImpl implements IAlmacenService {
    @Autowired
    IAlmacenRepository almacenRepository;

    @Override
    public Page<Almacen> listarAlmacenes(Pageable pageable) throws Exception {
        return almacenRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public Almacen obtenerAlmacen(Integer idAlmacen) throws Exception {
        return almacenRepository.findOneByIdAlmacenAndEstadoAuditoria(idAlmacen, "1");
    }

    @Override
    public Almacen insertarAlmacen(Almacen almacen) throws Exception {
        return almacenRepository.save(almacen);
    }

    @Override
    public Almacen actualizarAlmacen(Almacen almacen) throws Exception {
        return almacenRepository.save(almacen);
    }

    @Override
    public void darBajaAlmacen(Integer idAlmacen) throws Exception {
        almacenRepository.darBajaAlmacen(idAlmacen);
    }

}
