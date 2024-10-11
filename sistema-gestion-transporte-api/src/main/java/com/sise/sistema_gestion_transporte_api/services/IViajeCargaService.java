package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.ViajeCarga;

public interface IViajeCargaService {
    Page<ViajeCarga> listarViajesCargas(Pageable pageable) throws Exception;
    ViajeCarga obtenerViajeCarga(Integer idViajeCarga) throws Exception;
    ViajeCarga insertarViajeCarga(ViajeCarga viajeCarga) throws Exception;
    ViajeCarga actualizarViajeCarga(ViajeCarga viajeCarga) throws Exception;
    void darBajaViajeCarga(Integer idViajeCarga) throws Exception;
    
}
