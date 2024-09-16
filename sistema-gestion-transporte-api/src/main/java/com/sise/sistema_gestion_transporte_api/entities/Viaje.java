package com.sise.sistema_gestion_transporte_api.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "viajes")
public class Viaje {
    @Id
    @Column(name = "id_viaje")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idViaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conductor", referencedColumnName = "id_conductor")
    private Conductor conductor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    private Ruta ruta;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Column(name = "fecha_arribo")
    private LocalDateTime fechaArribo;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;

}
