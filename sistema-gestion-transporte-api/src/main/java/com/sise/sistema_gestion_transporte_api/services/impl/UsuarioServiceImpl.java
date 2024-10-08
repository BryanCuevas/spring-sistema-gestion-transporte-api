package com.sise.sistema_gestion_transporte_api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.sistema_gestion_transporte_api.entities.Usuario;
import com.sise.sistema_gestion_transporte_api.repositories.IUsuarioRepository;
import com.sise.sistema_gestion_transporte_api.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public Page<Usuario> listarUsuarios(Pageable pageable) throws Exception {
        return usuarioRepository.findByEstadoAuditoria("1", pageable);
    }

    @Override
    public Usuario obtenerUsuario(Integer idUsuario) throws Exception {
        return usuarioRepository.findOneByIdUsuarioAndEstadoAuditoria(idUsuario, "1");
    }

    @Override
    public Usuario insertarUsuario(Usuario usuario) throws Exception {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws Exception {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void darBajaUsuario(Integer idUsuario) throws Exception {
        usuarioRepository.darBajaUsuario(idUsuario);
    }

}
