package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Empresa;

public interface IEmpresaService {
    Page<Empresa> listarEmpresas(Pageable pageable) throws Exception;
    Empresa obtenerEmpresa(Integer idEmpresa) throws Exception;
    Empresa insertarEmpresa(Empresa empresa) throws Exception;
    Empresa actualizarEmpresa(Empresa empresa) throws Exception;
    void darBajaEmpresa(Integer idEmpresa) throws Exception;

}
