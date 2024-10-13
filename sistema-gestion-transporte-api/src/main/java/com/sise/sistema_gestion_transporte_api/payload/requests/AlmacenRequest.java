package com.sise.sistema_gestion_transporte_api.payload.requests;

import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlmacenRequest {
    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚ\\s]+$", message = "debe contener solo letras")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String departamento;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñ\\s]+$", message = "debe contener solo letras")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String provincia;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "debe contener solo letras")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String distrito;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "debe contener solo letras")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String nombre;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^-\\d+(\\.\\d+)?$", message = "debe ser válido para Perú")    
    private String latitud;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^-\\d+(\\.\\d+)?$", message = "debe ser válido para Perú")
    private String longitud;

    @NotBlank(message = "no puede estar vacío")
    @URL(message = "debe tener un formato válido")
    private String foto;
}
