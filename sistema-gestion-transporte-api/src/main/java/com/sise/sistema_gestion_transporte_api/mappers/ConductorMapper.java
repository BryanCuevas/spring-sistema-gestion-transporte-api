package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;
import com.sise.sistema_gestion_transporte_api.payload.requests.ConductorRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class ConductorMapper implements IMapperBase<Conductor, ConductorRequest> {

    @Override
    public Conductor requestToEntity(ConductorRequest request) {
        Conductor conductor = new Conductor();
        conductor.setUsuario(request.getUsuario().getIdUsuario());
        conductor.setNombres(request.getNombres());
        conductor.setApellidoPaterno(request.getApellidoPaterno());
        conductor.setApellidoMaterno(request.getApellidoMaterno());
        conductor.setDocumentoIdentidad(request.getDocumentoIdentidad());
        conductor.setTelefono(request.getTelefono());
        conductor.setCorreo(request.getCorreo());
        conductor.setLicencia(request.getLicencia());
        conductor.setFoto(request.getFoto());

        return conductor;
    }

}
