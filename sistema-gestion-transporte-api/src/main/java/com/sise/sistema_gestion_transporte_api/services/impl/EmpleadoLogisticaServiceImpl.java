package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.EmpleadoLogistica;
import com.sise.sistema_gestion_transporte_api.repositories.IEmpleadoLogisticaRepository;
import com.sise.sistema_gestion_transporte_api.services.IEmpleadoLogisticaService;

@Service
public class EmpleadoLogisticaServiceImpl implements IEmpleadoLogisticaService {
    @Autowired
    IEmpleadoLogisticaRepository empleadoLogisticaRepository;

    @Override
    public Page<EmpleadoLogistica> listarEmpleadosLogistica(Pageable pageable) throws Exception {
        return empleadoLogisticaRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public EmpleadoLogistica obtenerEmpleadoLogistica(Integer idEmpleadoLogistica) throws Exception {
        return empleadoLogisticaRepository.findOneByIdEmpleadoLogisticaAndEstadoAuditoria(idEmpleadoLogistica,"1");
    }

    @Override
    public EmpleadoLogistica insertarEmpleadoLogistica(EmpleadoLogistica empleadoLogistica) throws Exception {
        return empleadoLogisticaRepository.save(empleadoLogistica);
    }

    @Override
    public EmpleadoLogistica actualizarEmpleadoLogistica(EmpleadoLogistica empleadoLogistica) throws Exception {
        return empleadoLogisticaRepository.save(empleadoLogistica);
    }

    @Override
    public void darBajaEmpleadoLogistica(Integer idEmpleadoLogistica) throws Exception {
        empleadoLogisticaRepository.darBajaEmpleadoLogistica(idEmpleadoLogistica);
    }

}
