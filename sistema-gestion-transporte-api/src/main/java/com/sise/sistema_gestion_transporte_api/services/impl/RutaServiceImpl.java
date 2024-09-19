package com.sise.sistema_gestion_transporte_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Ruta;
import com.sise.sistema_gestion_transporte_api.repositories.IRutaRepository;
import com.sise.sistema_gestion_transporte_api.services.IRutaService;

@Service
public class RutaServiceImpl implements IRutaService {
    @Autowired
    IRutaRepository rutaRepository;

    @Override
    public List<Ruta> listarRutas() throws Exception {
        return rutaRepository.findByEstadoAuditoria("1");
    }

    @Override
    public Ruta obtenerRuta(Integer idRuta) throws Exception {
        return rutaRepository.findOneByIdRutaAndEstadoAuditoria(idRuta,"1");
    }

    @Override
    public Ruta insertarRuta(Ruta ruta) throws Exception {
        return rutaRepository.save(ruta);
    }

    @Override
    public Ruta actualizarRuta(Ruta ruta) throws Exception {
        return rutaRepository.save(ruta);
    }

    @Override
    public void darBajaRuta(Integer idRuta) throws Exception {
        rutaRepository.darBajaRuta(idRuta);
    }

}
