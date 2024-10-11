package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Empresa;
import com.sise.sistema_gestion_transporte_api.payload.requests.EmpresaRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class EmpresaMapper implements IMapperBase<Empresa, EmpresaRequest> {

    @Override
    public Empresa requestToEntity(EmpresaRequest request) {
        Empresa empresa = new Empresa();
        empresa.setRuc(request.getRuc());
        empresa.setRazonSocial(request.getRazonSocial());
        empresa.setNombreComercial(request.getNombreComercial());
        empresa.setDireccionFiscal(request.getDireccionFiscal());
        empresa.setTelefono(request.getTelefono());
        empresa.setCorreo(request.getCorreo());

        return empresa;
    }

}
