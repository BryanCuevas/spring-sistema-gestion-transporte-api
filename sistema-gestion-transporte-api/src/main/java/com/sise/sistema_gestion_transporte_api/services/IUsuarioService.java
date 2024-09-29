package com.sise.sistema_gestion_transporte_api.services;

import java.util.List;

import com.sise.sistema_gestion_transporte_api.entities.Usuario;

public interface IUsuarioService {
    List<Usuario> listarUsuarios() throws Exception;
    Usuario obtenerUsuario(Integer idUsuario) throws Exception;
    Usuario insertarUsuario(Usuario usuario) throws Exception;
    Usuario actualizarUsuario(Usuario usuario) throws Exception;
    void darBajaUsuario(Integer idUsuario) throws Exception;   

}
