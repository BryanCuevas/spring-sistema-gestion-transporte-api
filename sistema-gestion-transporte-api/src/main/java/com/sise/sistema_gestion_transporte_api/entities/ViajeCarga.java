package com.sise.sistema_gestion_transporte_api.entities;

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
@Table(name = "viajes_cargas")
public class ViajeCarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje_carga")
    private Integer idViajeCarga;

    @ManyToOne
    @JoinColumn(name = "id_viaje", referencedColumnName = "id_viaje")
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "id_carga", referencedColumnName = "id_carga")
    private Carga carga;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    public void setViaje(Integer idViaje) {
        this.viaje = new Viaje();
        this.viaje.setIdViaje(idViaje);
    }

    public void setCarga(Integer idCarga) {
        this.carga = new Carga();
        this.carga.setIdCarga(idCarga);
    }

}
