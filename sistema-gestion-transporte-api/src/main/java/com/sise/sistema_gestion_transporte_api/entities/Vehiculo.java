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
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @Column(name = "id_vehiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    @Column(name = "tipo_vehiculo")
    private String tipoVehiculo;

    @Column(name = "placa")
    private String placa;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "soat")
    private String soat;

    @Column(name = "peso_bruto")
    private String pesoBruto;

    @Column(name = "peso_neto")
    private String pesoNeto;

    @Column(name = "foto")
    private String foto;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehiculo")
    @JsonIgnore
    private List<Viaje> viajesAsignados;

}
