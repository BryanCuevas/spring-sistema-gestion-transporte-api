package com.sise.sistema_gestion_transporte_api.payload.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TipoIncidenciaRequest {
    @NotBlank(message = "no puede estar vacío")
    @Size(min = 5, message = "debe tener cinco o más caracteres")
    private String detalle;

}
