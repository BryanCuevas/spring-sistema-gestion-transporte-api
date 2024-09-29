package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;

public interface IAlmacenService {
    Page<Almacen> listarAlmacenes(Pageable pageable) throws Exception;
    Almacen obtenerAlmacen(Integer idAlmacen) throws Exception;
    Almacen insertarAlmacen(Almacen almacen) throws Exception;
    Almacen actualizarAlmacen(Almacen almacen) throws Exception;
    void darBajaAlmacen(Integer idAlmacen) throws Exception;   

}
