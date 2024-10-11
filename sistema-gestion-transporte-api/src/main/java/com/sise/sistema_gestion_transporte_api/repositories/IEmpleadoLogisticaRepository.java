package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.EmpleadoLogistica;

import jakarta.transaction.Transactional;

@Repository
public interface IEmpleadoLogisticaRepository extends JpaRepository<EmpleadoLogistica, Integer> {
    Page<EmpleadoLogistica> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    EmpleadoLogistica findOneByIdEmpleadoLogisticaAndEstadoAuditoria(Integer idEmpleadoLogistica, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE EmpleadoLogistica el SET el.estadoAuditoria = '0' WHERE el.idEmpleadoLogistica = :idEmpleadoLogistica")
    void darBajaEmpleadoLogistica(@Param("idEmpleadoLogistica") Integer idEmpleadoLogistica);
}
