package com.sise.sistema_gestion_transporte_api.services;

import java.util.List;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;

public interface IViajeService {
    List<Viaje> listarViajes() throws Exception;
    Viaje obtenerViaje(Integer idViaje) throws Exception;
    Viaje insertarViaje(Viaje viaje) throws Exception;
    Viaje actualizarViaje(Viaje viaje) throws Exception;
    void darBajaViaje(Integer idViaje) throws Exception;    

}
