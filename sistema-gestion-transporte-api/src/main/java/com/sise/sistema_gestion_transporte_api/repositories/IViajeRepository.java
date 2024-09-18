package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Viaje;

@Repository
public interface IViajeRepository extends JpaRepository<Viaje, Integer> {

}
