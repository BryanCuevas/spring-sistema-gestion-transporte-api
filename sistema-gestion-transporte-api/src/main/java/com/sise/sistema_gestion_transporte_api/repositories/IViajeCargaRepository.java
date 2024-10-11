package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.ViajeCarga;

import jakarta.transaction.Transactional;

@Repository
public interface IViajeCargaRepository extends JpaRepository<ViajeCarga, Integer> {
    Page<ViajeCarga> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    ViajeCarga findOneByIdViajeCargaAndEstadoAuditoria(Integer idViajeCarga, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE ViajeCarga vc SET vc.estadoAuditoria = '0' WHERE vc.idViajeCarga = :idViajeCarga")
    void darBajaViajeCarga(@Param("idViajeCarga") Integer idViajeCarga);
}
