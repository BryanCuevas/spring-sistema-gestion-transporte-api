package com.sise.sistema_gestion_transporte_api.payload.requests;

import com.sise.sistema_gestion_transporte_api.entities.Empresa;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CargaRequest {
    @NotBlank(message = "no puede estar vacío")
    private Empresa empresa;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "[GPF]", message = "debe ser 'G' (General), 'P' (Perecedera) o 'F' (Frágil)")
    private String tipoCarga;

    @NotBlank(message = "no puede estar vacío")
    private String descripcion;

    @NotBlank(message = "no puede estar vacío")
    @Min(value = 1, message = "debe ser un valor positivo")
    @Max(value = 15000, message = "no puede exceder las 15 toneladas")
    @Digits(integer = 5, fraction = 0, message = "debe ser un número entero (política de MovilPerú)")
    private Integer peso;

}
