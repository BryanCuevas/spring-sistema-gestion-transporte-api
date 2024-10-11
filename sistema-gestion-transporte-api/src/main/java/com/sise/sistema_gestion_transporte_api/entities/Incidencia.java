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
@Table(name = "incidencias")
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia")
    private Integer idIncidencia;

    @ManyToOne
    @JoinColumn(name = "id_tipo_incidencia", referencedColumnName = "id_tipo_incidencia")
    private TipoIncidencia tipoIncidencia;

    @ManyToOne
    @JoinColumn(name = "id_viaje", referencedColumnName = "id_viaje")
    private Viaje viaje;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    public void setTipoIncidencia(Integer idTipoIncidencia) {
        this.tipoIncidencia = new TipoIncidencia();
        this.tipoIncidencia.setIdTipoIncidencia(idTipoIncidencia);
    }

    public void setViaje(Integer idViaje) {
        this.viaje = new Viaje();
        this.viaje.setIdViaje(idViaje);
    }

}
