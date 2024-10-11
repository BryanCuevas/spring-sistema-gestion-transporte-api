package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Empresa;

import jakarta.transaction.Transactional;

@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {
    Page<Empresa> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    Empresa findOneByIdEmpresaAndEstadoAuditoria(Integer idEmpresa, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Empresa e SET e.estadoAuditoria = '0' WHERE e.idEmpresa = :idEmpresa")
    void darBajaEmpresa(@Param("idEmpresa") Integer idEmpresa);
}
