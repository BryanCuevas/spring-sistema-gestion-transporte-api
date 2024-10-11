package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.ViajeCarga;
import com.sise.sistema_gestion_transporte_api.payload.requests.ViajeCargaRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class ViajeCargaMapper implements IMapperBase<ViajeCarga, ViajeCargaRequest> {

    @Override
    public ViajeCarga requestToEntity(ViajeCargaRequest request) {
        ViajeCarga viajeCarga = new ViajeCarga();
        viajeCarga.setViaje(request.getViaje().getIdViaje());
        viajeCarga.setCarga(request.getCarga().getIdCarga());

        return viajeCarga;
    }

}
