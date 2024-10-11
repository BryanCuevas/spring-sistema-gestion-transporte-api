package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;
import com.sise.sistema_gestion_transporte_api.payload.requests.AlmacenRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class AlmacenMapper implements IMapperBase<Almacen, AlmacenRequest> {

    @Override
    public Almacen requestToEntity(AlmacenRequest request) {
        Almacen almacen = new Almacen();
        almacen.setDepartamento(request.getDepartamento());
        almacen.setProvincia(request.getProvincia());
        almacen.setDistrito(request.getDistrito());
        almacen.setNombre(request.getNombre());
        almacen.setLatitud(request.getLatitud());
        almacen.setLongitud(request.getLongitud());
        almacen.setFoto(request.getFoto());

        return almacen;
    }

}
