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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "conductores")
public class Conductor {
    @Id
    @Column(name = "id_conductor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConductor;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "documento_identidad")
    private String documentoIdentidad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "licencia")
    private String licencia;

    @Column(name = "foto")
    private String foto;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conductor")
    @JsonIgnore
    private List<Viaje> viajesAsignados;

    public void setIdUsuario(Integer idUsuario) {
        this.usuario = new Usuario();
        this.usuario.setIdUsuario(idUsuario);
    }

}
