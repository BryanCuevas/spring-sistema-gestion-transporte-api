package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;

import jakarta.transaction.Transactional;

@Repository
public interface IViajeRepository extends JpaRepository<Viaje, Integer> {
    Page<Viaje> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    Viaje findOneByIdViajeAndEstadoAuditoria(Integer idViaje, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Viaje v SET v.estadoAuditoria = '0' WHERE v.idViaje = :idViaje")
    void darBajaViaje(@Param("idViaje") Integer idViaje);

}
