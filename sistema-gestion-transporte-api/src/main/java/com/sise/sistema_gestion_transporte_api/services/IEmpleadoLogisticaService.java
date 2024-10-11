package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.EmpleadoLogistica;

public interface IEmpleadoLogisticaService {
    Page<EmpleadoLogistica> listarEmpleadosLogistica(Pageable pageable) throws Exception;
    EmpleadoLogistica obtenerEmpleadoLogistica(Integer idEmpleadoLogistica) throws Exception;
    EmpleadoLogistica insertarEmpleadoLogistica(EmpleadoLogistica empleadoLogistica) throws Exception;
    EmpleadoLogistica actualizarEmpleadoLogistica(EmpleadoLogistica empleadoLogistica) throws Exception;
    void darBajaEmpleadoLogistica(Integer idEmpleadoLogistica) throws Exception;

}
