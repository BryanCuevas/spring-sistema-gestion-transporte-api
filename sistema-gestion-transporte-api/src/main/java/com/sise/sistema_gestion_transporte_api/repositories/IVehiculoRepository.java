package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;

import jakarta.transaction.Transactional;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    Page<Vehiculo> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    Vehiculo findOneByIdVehiculoAndEstadoAuditoria(Integer idVehiculo, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Vehiculo v SET v.estadoAuditoria = '0' WHERE v.idVehiculo = :idVehiculo")
    void darBajaVehiculo(@Param("idVehiculo") Integer idVehiculo);

}
