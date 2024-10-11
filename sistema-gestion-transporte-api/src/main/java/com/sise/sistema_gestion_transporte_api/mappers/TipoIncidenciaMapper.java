package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.TipoIncidencia;
import com.sise.sistema_gestion_transporte_api.payload.requests.TipoIncidenciaRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class TipoIncidenciaMapper implements IMapperBase<TipoIncidencia, TipoIncidenciaRequest> {

    @Override
    public TipoIncidencia requestToEntity(TipoIncidenciaRequest request) {
        TipoIncidencia tipoIncidencia = new TipoIncidencia();
        tipoIncidencia.setDetalle(request.getDetalle());

        return tipoIncidencia;
    }

}
