package com.sise.sistema_gestion_transporte_api.services;

import java.util.List;

import com.sise.sistema_gestion_transporte_api.entities.Ruta;

public interface IRutaService {
    List<Ruta> listarRutas() throws Exception;
    Ruta obtenerRuta(Integer idRuta) throws Exception;
    Ruta insertarRuta(Ruta ruta) throws Exception;
    Ruta actualizarRuta(Ruta ruta) throws Exception;
    void darBajaRuta(Integer idRuta) throws Exception;    

}
