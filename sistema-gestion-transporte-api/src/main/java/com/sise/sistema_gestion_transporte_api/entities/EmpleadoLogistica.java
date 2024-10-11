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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "empleados_logistica")
public class EmpleadoLogistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado_logistica")
    private Integer idEmpleadoLogistica;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    private Empresa empresa;

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

    @Column(name = "foto")
    private String foto;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    public void setEmpresa(Integer idEmpresa) {
        this.empresa = new Empresa();
        this.empresa.setIdEmpresa(idEmpresa);
    }

    public void setUsuario(Integer idUsuario) {
        this.usuario = new Usuario();
        this.usuario.setIdUsuario(idUsuario);
    }
}
