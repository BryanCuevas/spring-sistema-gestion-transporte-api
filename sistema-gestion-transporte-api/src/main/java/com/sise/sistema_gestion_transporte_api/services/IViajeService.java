package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;

public interface IViajeService {
    Page<Viaje> listarViajes(Pageable pageable) throws Exception;
    Viaje obtenerViaje(Integer idViaje) throws Exception;
    Viaje insertarViaje(Viaje viaje) throws Exception;
    Viaje actualizarViaje(Viaje viaje) throws Exception;
    void darBajaViaje(Integer idViaje) throws Exception;    

}
