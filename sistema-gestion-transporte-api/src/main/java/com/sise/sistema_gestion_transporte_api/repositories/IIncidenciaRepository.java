package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Incidencia;

import jakarta.transaction.Transactional;

@Repository
public interface IIncidenciaRepository extends JpaRepository<Incidencia, Integer> {
    Page<Incidencia> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    Incidencia findOneByIdIncidenciaAndEstadoAuditoria(Integer idIncidencia, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Incidencia i SET i.estadoAuditoria = '0' WHERE i.idIncidencia = :idIncidencia")
    void darBajaIncidencia(@Param("idIncidencia") Integer idIncidencia);
}
