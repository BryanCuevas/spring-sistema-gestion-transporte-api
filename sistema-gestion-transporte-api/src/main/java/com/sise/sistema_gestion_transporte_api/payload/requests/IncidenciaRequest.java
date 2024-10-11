package com.sise.sistema_gestion_transporte_api.payload.requests;

import com.sise.sistema_gestion_transporte_api.entities.TipoIncidencia;
import com.sise.sistema_gestion_transporte_api.entities.Viaje;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IncidenciaRequest {
    @NotBlank(message = "no puede estar vacío")
    private TipoIncidencia tipoIncidencia;

    @NotBlank(message = "no puede estar vacío")
    private Viaje viaje;

    @NotBlank(message = "no puede estar vacío")
    @Size(min = 10, message = "debe tener diez o más caracteres")
    private String descripcion;

}
