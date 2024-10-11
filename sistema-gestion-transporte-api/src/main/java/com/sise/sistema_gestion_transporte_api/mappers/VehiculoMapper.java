package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;
import com.sise.sistema_gestion_transporte_api.payload.requests.VehiculoRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class VehiculoMapper implements IMapperBase<Vehiculo, VehiculoRequest>{

    @Override
    public Vehiculo requestToEntity(VehiculoRequest request) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipoVehiculo(request.getTipoVehiculo());
        vehiculo.setPlaca(request.getPlaca());
        vehiculo.setModelo(request.getModelo());
        vehiculo.setSoat(request.getSoat());
        vehiculo.setPesoBruto(request.getPesoBruto());
        vehiculo.setPesoNeto(request.getPesoNeto());
        vehiculo.setFoto(request.getFoto());

        return vehiculo;
    }

}
