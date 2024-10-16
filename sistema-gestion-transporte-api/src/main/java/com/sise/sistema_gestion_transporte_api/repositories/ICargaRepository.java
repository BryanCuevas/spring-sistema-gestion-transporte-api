package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Carga;

import jakarta.transaction.Transactional;

@Repository
public interface ICargaRepository extends JpaRepository<Carga, Integer> {
    Page<Carga> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    Carga findOneByIdCargaAndEstadoAuditoria(Integer idCarga, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Carga c SET c.estadoAuditoria = '0' WHERE c.idCarga = :idCargas")
    void darBajaCarga(@Param("idCarga") Integer idCarga);
}
