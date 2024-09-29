package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Usuario;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByEstadoAuditoria(String estadoAuditoria);

    Usuario findOneByIdUsuarioAndEstadoAuditoria(Integer idUsuario, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.estadoAuditoria = '0' WHERE u.idUsuario = :idUsuario")
    void darBajaUsuario(@Param("idUsuario") Integer idUsuario);

}
