package com.sise.sistema_gestion_transporte_api.payload.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpresaRequest {
    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[0-9]{11}$", message = "debe ser numérico y contener exactamente 11 dígitos")
    private String ruc;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z\\.]+$", message = "debe tener un formato válido")
    @Size(min = 5, message = "debe tener tres o más caracteres")
    private String razonSocial;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z\\.]+$", message = "debe tener un foramto válido")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String nombreComercial;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,\\.-°]+$", message = "debe tener un formato válido")
    @Size(min = 10, message = "debe tener tres o más caracteres")
    private String direccionFiscal;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[0-9]{9}$", message = "debe ser numérico y contener exactamente 9 dígitos")
    private String telefono;

    @NotBlank(message = "no puede estar vacío")
    @Email(message = "debe tener un formato válido")
    private String correo;

}
