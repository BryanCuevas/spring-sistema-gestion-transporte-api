package com.sise.sistema_gestion_transporte_api.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_conductor", referencedColumnName = "id_conductor")
    private Conductor conductor;

    @ManyToOne
    @JoinColumn(name = "id_ruta", referencedColumnName = "id_ruta")
    private Ruta ruta;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "fecha_arribo")
    private LocalDate fechaArribo;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    public void setVehiculo(Integer idVehiculo) {
        this.vehiculo = new Vehiculo();
        this.vehiculo.setIdVehiculo(idVehiculo);
    }

    public void setConductor(Integer idConductor) {
        this.vehiculo = new Vehiculo();
        this.vehiculo.setIdVehiculo(idConductor);
    }

    public void setRuta(Integer idRuta) {
        this.vehiculo = new Vehiculo();
        this.vehiculo.setIdVehiculo(idRuta);
    }

}
