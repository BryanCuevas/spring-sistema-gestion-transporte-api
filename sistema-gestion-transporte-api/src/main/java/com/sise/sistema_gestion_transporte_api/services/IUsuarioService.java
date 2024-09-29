package com.sise.sistema_gestion_transporte_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sise.sistema_gestion_transporte_api.entities.Usuario;

public interface IUsuarioService {
    Page<Usuario> listarUsuarios(Pageable pageable) throws Exception;
    Usuario obtenerUsuario(Integer idUsuario) throws Exception;
    Usuario insertarUsuario(Usuario usuario) throws Exception;
    Usuario actualizarUsuario(Usuario usuario) throws Exception;
    void darBajaUsuario(Integer idUsuario) throws Exception;   

}
