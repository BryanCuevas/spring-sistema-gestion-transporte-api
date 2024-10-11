package com.sise.sistema_gestion_transporte_api.payload.requests;

import com.sise.sistema_gestion_transporte_api.entities.Carga;
import com.sise.sistema_gestion_transporte_api.entities.Viaje;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ViajeCargaRequest {

    @NotBlank(message = "no puede estar vacío")
    private Viaje viaje;

    @NotBlank(message = "no puede estar vacío")
    private Carga carga;

}
