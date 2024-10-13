package com.sise.sistema_gestion_transporte_api.payload.requests;

import com.sise.sistema_gestion_transporte_api.entities.Almacen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RutaRequest {
    private Almacen almacenOrigen;

    private Almacen almacenDestino;

    @NotBlank(message = "no puede estar vacío")
    @Size(min = 5, message = "debe tener cinco o más caracteres")
    private String nombre;

    @NotBlank(message = "no puede estar vacío")
    @Pattern(regexp = "^[AI]$", message = "debe ser 'A' (Activa) o 'I' (Inactiva)")
    private String estadoRuta;

    public void setAlmacenOrigen(Integer idAlmacenOrigen) {
        this.almacenOrigen = new Almacen();
        this.almacenOrigen.setIdAlmacen(idAlmacenOrigen);
    }

    public void setAlmacenDestino(Integer idAlmacenDestino) {
        this.almacenDestino = new Almacen();
        this.almacenDestino.setIdAlmacen(idAlmacenDestino);
    }
}
