package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface IAlmacenRepository extends JpaRepository<Almacen, Integer> {
    List<Almacen> findByEstadoAuditoria(String estadoAuditoria);

    Almacen findOneByIdAlmacenAndEstadoAuditoria(Integer idAlmacen, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Almacen a SET a.estadoAuditoria = '0' WHERE a.idAlmacen = :idAlmacen")
    void darBajaAlmacen(@Param("idAlmacen") Integer idAlmacen);

}
