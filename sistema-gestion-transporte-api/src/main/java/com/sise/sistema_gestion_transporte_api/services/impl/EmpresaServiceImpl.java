package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Empresa;
import com.sise.sistema_gestion_transporte_api.repositories.IEmpresaRepository;
import com.sise.sistema_gestion_transporte_api.services.IEmpresaService;

@Service
public class EmpresaServiceImpl implements IEmpresaService {
    @Autowired
    IEmpresaRepository empresaRepository;

    @Override
    public Page<Empresa> listarEmpresas(Pageable pageable) throws Exception {
        return empresaRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public Empresa obtenerEmpresa(Integer idEmpresa) throws Exception {
        return empresaRepository.findOneByIdEmpresaAndEstadoAuditoria(idEmpresa, "1");
    }

    @Override
    public Empresa insertarEmpresa(Empresa empresa) throws Exception {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa actualizarEmpresa(Empresa empresa) throws Exception {
        return empresaRepository.save(empresa);
    }

    @Override
    public void darBajaEmpresa(Integer idEmpresa) throws Exception {
        empresaRepository.darBajaEmpresa(idEmpresa);
    }

}
