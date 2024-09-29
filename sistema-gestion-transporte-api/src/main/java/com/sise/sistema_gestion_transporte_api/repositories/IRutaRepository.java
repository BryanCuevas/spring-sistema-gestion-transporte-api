package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Ruta;

import jakarta.transaction.Transactional;

@Repository
public interface IRutaRepository extends JpaRepository<Ruta, Integer> {
    Page<Ruta> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    Ruta findOneByIdRutaAndEstadoAuditoria(Integer idRuta, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Ruta r SET r.estadoAuditoria = '0' WHERE r.idRuta = :idRuta")
    void darBajaRuta(@Param("idRuta") Integer idRuta);

}
