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
@Table(name = "rutas")
public class Ruta {
    @Id
    @Column(name = "id_ruta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRuta;

    @ManyToOne
    @JoinColumn(name = "id_almacen_origen", referencedColumnName = "id_almacen")
    private Almacen almacenOrigen;

    @ManyToOne
    @JoinColumn(name = "id_almacen_destino", referencedColumnName = "id_almacen")
    private Almacen almacenDestino;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ruta")
    @JsonIgnore
    private List<Viaje> viajesAsignados;

    public void setAlmacenOrigen(Integer idAlmacenOrigen) {
        this.almacenOrigen = new Almacen();
        this.almacenOrigen.setIdAlmacen(idAlmacenOrigen);
    }

    public void setAlmacenDestino(Integer idAlmacenDestino) {
        this.almacenDestino = new Almacen();
        this.almacenDestino.setIdAlmacen(idAlmacenDestino);
    }

}
