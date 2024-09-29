package com.sise.sistema_gestion_transporte_api.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "almacenes")
public class Almacen {
    @Id
    @Column(name = "id_almacen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlmacen;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;

    @Column(name = "foto")
    private String foto;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "almacenOrigen")
    @JsonIgnore
    private List<Ruta> rutasOrigen;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "almacenDestino")
    @JsonIgnore
    private List<Ruta> rutasDestino;

}
