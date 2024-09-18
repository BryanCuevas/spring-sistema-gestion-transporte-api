package com.sise.sistema_gestion_transporte_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;

@Repository
public interface IAlmacenRepository extends JpaRepository<Almacen, Integer> {

}
