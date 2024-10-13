package com.sise.sistema_gestion_transporte_api.payload.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Debe contener solo letras y números")
    @Size(min = 5, message = "debe tener cinco o más caracteres")
    private String nombreUsuario;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[^\\s]+$", message = "no acepta espacios")
    @Size(min = 5, message = "debe tener cinco o más caracteres")
    private String clave;

}
