package com.sise.sistema_gestion_transporte_api.payload.requests;

import com.sise.sistema_gestion_transporte_api.entities.TipoIncidencia;
import com.sise.sistema_gestion_transporte_api.entities.Viaje;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IncidenciaRequest {
    private TipoIncidencia tipoIncidencia;

    private Viaje viaje;

    @NotBlank(message = "no puede estar vacío")
    @Size(min = 10, message = "debe tener diez o más caracteres")
    private String descripcion;

    public void setTipoIncidencia(Integer idTipoIncidencia) {
        this.tipoIncidencia = new TipoIncidencia();
        this.tipoIncidencia.setIdTipoIncidencia(idTipoIncidencia);
    }

    public void setViaje(Integer idViaje) {
        this.viaje = new Viaje();
        this.viaje.setIdViaje(idViaje);
    }

}
