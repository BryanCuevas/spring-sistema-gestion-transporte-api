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
@Table(name = "cargas")
public class Carga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carga")
    private Integer idCarga;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    private Empresa empresa;

    @Column(name = "tipo_carga")
    private String tipoCarga;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "peso")
    private Integer peso;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carga")
    @JsonIgnore
    private List<ViajeCarga> cargas;

    public void setEmpresa(Integer idEmpresa) {
        this.empresa = new Empresa();
        this.empresa.setIdEmpresa(idEmpresa);
    }

}
