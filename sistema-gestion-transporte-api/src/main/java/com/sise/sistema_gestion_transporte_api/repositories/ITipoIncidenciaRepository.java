package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.TipoIncidencia;

import jakarta.transaction.Transactional;

@Repository
public interface ITipoIncidenciaRepository extends JpaRepository<TipoIncidencia, Integer> {
    Page<TipoIncidencia> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    TipoIncidencia findOneByIdTipoIncidenciaAndEstadoAuditoria(Integer idTipoIncidencia, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE TipoIncidencia ti SET ti.estadoAuditoria = '0' WHERE ti.idTipoIncidencia = :idTipoIncidencia")
    void darBajaTipoIncidencia(@Param("idTipoIncidencia") Integer idTipoIncidencia);

}
