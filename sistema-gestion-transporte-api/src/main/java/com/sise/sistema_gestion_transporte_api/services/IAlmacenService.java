package com.sise.sistema_gestion_transporte_api.services;

import java.util.List;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;

public interface IAlmacenService {
    List<Almacen> listarAlmacenes() throws Exception;
    Almacen obtenerAlmacen(Integer idAlmacen) throws Exception;
    Almacen insertarAlmacen(Almacen almacen) throws Exception;
    Almacen actualizarAlmacen(Almacen almacen) throws Exception;
    void darBajaAlmacen(Integer idAlmacen) throws Exception;   

}
