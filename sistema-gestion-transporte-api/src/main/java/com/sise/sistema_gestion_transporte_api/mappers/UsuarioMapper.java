package com.sise.sistema_gestion_transporte_api.mappers;

import com.sise.sistema_gestion_transporte_api.entities.Usuario;
import com.sise.sistema_gestion_transporte_api.payload.requests.UsuarioRequest;
import com.sise.sistema_gestion_transporte_api.shared.IMapperBase;

public class UsuarioMapper implements IMapperBase<Usuario, UsuarioRequest> {

    @Override
    public Usuario requestToEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setClave(request.getClave());

        return usuario;
    }

}
