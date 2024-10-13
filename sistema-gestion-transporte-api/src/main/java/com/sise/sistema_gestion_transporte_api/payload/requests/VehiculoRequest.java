package com.sise.sistema_gestion_transporte_api.payload.requests;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VehiculoRequest {
    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "[LSP]", message = "debe ser 'L' (Ligero), 'S' (Semipesado) o 'P' (Pesado)")
    private String tipoVehiculo;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{3}$", message = "debe tener un formato válido")
    private String placa;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "debe contener solo letras y/o números")
    private String modelo;

    @NotBlank(message = "no puede estar vacío")
    private String soat;

    @NotNull(message = "no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "debe ser numérico")
    private Integer pesoBruto;

    @NotNull(message = "no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "debe ser numérico")
    private Integer pesoNeto;

    @NotBlank(message = "no puede estar vacío")
    @URL(message = "debe tener un formato válido")
    private String foto;

}
