package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Ruta;
import com.sise.sistema_gestion_transporte_api.payload.requests.RutaRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class RutaMapper implements IMapperBase<Ruta, RutaRequest> {

    @Override
    public Ruta requestToEntity(RutaRequest request) {
        Ruta ruta = new Ruta();
        ruta.setAlmacenOrigen(request.getAlmacenOrigen().getIdAlmacen());
        ruta.setAlmacenDestino(request.getAlmacenDestino().getIdAlmacen());
        ruta.setNombre(request.getNombre());
        ruta.setEstadoRuta(request.getEstadoRuta());;

        return ruta;
    }

}
