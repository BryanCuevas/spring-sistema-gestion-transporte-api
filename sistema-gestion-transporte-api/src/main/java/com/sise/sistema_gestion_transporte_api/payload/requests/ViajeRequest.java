package com.sise.sistema_gestion_transporte_api.payload.requests;

import java.time.LocalDateTime;

import com.sise.sistema_gestion_transporte_api.entities.Conductor;
import com.sise.sistema_gestion_transporte_api.entities.Ruta;
import com.sise.sistema_gestion_transporte_api.entities.Vehiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class ViajeRequest {
    @NotBlank(message = "no puede estar vacío")
    private Vehiculo vehiculo;

    @NotBlank(message = "no puede estar vacío")
    private Conductor conductor;

    @NotBlank(message = "no puede estar vacío")
    private Ruta ruta;

    @NotBlank(message = "no puede estar vacío")
    private LocalDateTime fechaProgramadaSalida;

    @NotBlank(message = "no puede estar vacío")
    private LocalDateTime fechaProgramadaArribo;

    private LocalDateTime fechaSalida;

    private LocalDateTime fechaArribo;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "[PEFCA]", message = "debe ser 'P' (Pendiente), 'E' (En curso), 'F' (Finalizado), 'C' (Cancelado) o 'A' (Atrasado)")
    private String estadoViaje;

}
