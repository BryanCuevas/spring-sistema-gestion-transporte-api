package com.sise.sistema_gestion_transporte_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;
import com.sise.sistema_gestion_transporte_api.repositories.IAlmacenRepository;
import com.sise.sistema_gestion_transporte_api.services.IAlmacenService;

@Service
public class AlmacenServiceImpl implements IAlmacenService {
    @Autowired
    IAlmacenRepository almacenRepository;

    @Override
    public List<Almacen> listarAlmacenes() throws Exception {
        return almacenRepository.findByEstadoAuditoria("1");
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
