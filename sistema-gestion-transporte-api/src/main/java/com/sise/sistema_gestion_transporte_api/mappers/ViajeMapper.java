package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;
import com.sise.sistema_gestion_transporte_api.payload.requests.ViajeRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class ViajeMapper implements IMapperBase<Viaje, ViajeRequest> {

    @Override
    public Viaje requestToEntity(ViajeRequest request) {
        Viaje viaje = new Viaje();
        viaje.setVehiculo(request.getVehiculo().getIdVehiculo());
        viaje.setConductor(request.getConductor().getIdConductor());
        viaje.setRuta(request.getRuta().getIdRuta());
        viaje.setFechaProgramadaSalida(request.getFechaProgramadaSalida());
        viaje.setFechaProgramadaArribo(request.getFechaProgramadaArribo());
        viaje.setFechaSalida(request.getFechaSalida());
        viaje.setFechaArribo(request.getFechaArribo());
        viaje.setEstadoViaje(request.getEstadoViaje());

        return viaje;
    }

}
