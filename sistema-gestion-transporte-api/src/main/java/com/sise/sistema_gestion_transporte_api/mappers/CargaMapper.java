package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Carga;
import com.sise.sistema_gestion_transporte_api.payload.requests.CargaRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class CargaMapper implements IMapperBase<Carga, CargaRequest> {

    @Override
    public Carga requestToEntity(CargaRequest request) {
        Carga carga = new Carga();
        carga.setEmpresa(request.getEmpresa().getIdEmpresa());
        carga.setTipoCarga(request.getTipoCarga());
        carga.setDescripcion(request.getDescripcion());
        carga.setPeso(request.getPeso());

        return carga;
    }

}
