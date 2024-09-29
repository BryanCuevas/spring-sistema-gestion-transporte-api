package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Ruta;

public interface IRutaService {
    Page<Ruta> listarRutas(Pageable pageable) throws Exception;
    Ruta obtenerRuta(Integer idRuta) throws Exception;
    Ruta insertarRuta(Ruta ruta) throws Exception;
    Ruta actualizarRuta(Ruta ruta) throws Exception;
    void darBajaRuta(Integer idRuta) throws Exception;    

}
