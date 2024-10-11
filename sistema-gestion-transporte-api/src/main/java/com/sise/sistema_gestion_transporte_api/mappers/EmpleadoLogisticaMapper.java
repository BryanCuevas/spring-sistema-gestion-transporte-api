package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.EmpleadoLogistica;
import com.sise.sistema_gestion_transporte_api.payload.requests.EmpleadoLogisticaRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class EmpleadoLogisticaMapper implements IMapperBase<EmpleadoLogistica, EmpleadoLogisticaRequest> {

    @Override
    public EmpleadoLogistica requestToEntity(EmpleadoLogisticaRequest request) {
        EmpleadoLogistica empleadoLogistica = new EmpleadoLogistica();
        empleadoLogistica.setEmpresa(request.getEmpresa().getIdEmpresa());
        empleadoLogistica.setUsuario(request.getUsuario().getIdUsuario());
        empleadoLogistica.setNombres(request.getNombres());
        empleadoLogistica.setApellidoPaterno(request.getApellidoPaterno());
        empleadoLogistica.setApellidoMaterno(request.getApellidoMaterno());
        empleadoLogistica.setDocumentoIdentidad(request.getDocumentoIdentidad());
        empleadoLogistica.setTelefono(request.getTelefono());
        empleadoLogistica.setCorreo(request.getCorreo());
        empleadoLogistica.setFoto(request.getFoto());

        return empleadoLogistica;
    }

}
