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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_conductor", referencedColumnName = "id_conductor")
    private Conductor conductor;

    @ManyToOne
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    private Ruta ruta;

    @Column(name = "fecha_programada_salida")
    private LocalDateTime fechaProgramadaSalida;

    @Column(name = "fecha_programada_arribo")
    private LocalDateTime fechaProgramadaArribo;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    @Column(name = "fecha_arribo")
    private LocalDateTime fechaArribo;

    @Column(name = "estado_viaje")
    private String estadoViaje;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    @JsonIgnore
    private List<Incidencia> incidencias;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "viaje")
    @JsonIgnore
    private List<ViajeCarga> viajes;

    public void setVehiculo(Integer idVehiculo) {
        this.vehiculo = new Vehiculo();
        this.vehiculo.setIdVehiculo(idVehiculo);
    }

    public void setConductor(Integer idConductor) {
        this.conductor = new Conductor();
        this.conductor.setIdConductor(idConductor);
    }

    public void setRuta(Integer idRuta) {
        this.ruta = new Ruta();
        this.ruta.setIdRuta(idRuta);
    }

}
