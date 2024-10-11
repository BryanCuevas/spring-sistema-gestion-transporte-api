package com.sise.sistema_gestion_transporte_api.payload.requests;

import org.hibernate.validator.constraints.URL;

import com.sise.sistema_gestion_transporte_api.entities.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConductorRequest {
    @NotBlank(message = "no puede estar vacío")
    private Usuario usuario;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "debe contener solo letras")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String nombres;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "debe contener solo letras")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "debe contener solo letras")
    @Size(min = 3, message = "debe tener tres o más caracteres")
    private String apellidoMaterno;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}$", message = "debe ser numérico y contener exactamente 8 dígitos")
    private String documentoIdentidad;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[0-9]{9}$", message = "debe ser numérico y contener exactamente 9 dígitos")
    private String telefono;

    @NotBlank(message = "no puede estar vacío")
    @Email(message = "debe tener un formato válido")
    private String correo;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[A-Z0-9]{5}$", message = "debe contener exactamente 5 caracteres")
    private String licencia;

    @NotBlank(message = "no puede estar vacío")
    @URL(message = "debe tener un formato válido")
    private String foto;

}
