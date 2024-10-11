package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Carga;

public interface ICargaService {
    Page<Carga> listarCargas(Pageable pageable) throws Exception;
    Carga obtenerCarga(Integer idCarga) throws Exception;
    Carga insertarCarga(Carga carga) throws Exception;
    Carga actualizarCarga(Carga carga) throws Exception;
    void darBajaCarga(Integer idCarga) throws Exception;

}
