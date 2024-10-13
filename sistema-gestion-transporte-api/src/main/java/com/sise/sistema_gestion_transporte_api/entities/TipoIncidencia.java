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
@Table(name = "tipos_incidencia")
public class TipoIncidencia {
    @Id
    @Column(name = "id_tipo_incidencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoIncidencia;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoIncidencia")
    @JsonIgnore
    private List<Incidencia> incidencias;

}
