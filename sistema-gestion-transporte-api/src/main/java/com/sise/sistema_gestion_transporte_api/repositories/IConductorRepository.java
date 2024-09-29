package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;

import jakarta.transaction.Transactional;

@Repository
public interface IConductorRepository extends JpaRepository<Conductor, Integer> {   
    Page<Conductor> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    Conductor findOneByIdConductorAndEstadoAuditoria(Integer idConductor, String estadoAuditoria);  

    @Modifying
    @Transactional
    @Query("UPDATE Conductor c SET c.estadoAuditoria = '0' WHERE c.idConductor = :idConductor")
    void darBajaConductor(@Param("idConductor") Integer idConductor);

}
