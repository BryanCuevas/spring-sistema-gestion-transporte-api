package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Incidencia;
import com.sise.sistema_gestion_transporte_api.payload.requests.IncidenciaRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class IncidenciaMapper implements IMapperBase<Incidencia, IncidenciaRequest> {

    @Override
    public Incidencia requestToEntity(IncidenciaRequest request) {
        Incidencia incidencia = new Incidencia();
        incidencia.setTipoIncidencia(request.getTipoIncidencia().getIdTipoIncidencia());
        incidencia.setViaje(request.getViaje().getIdViaje());
        incidencia.setDescripcion(request.getDescripcion());

        return incidencia;
    }

}
