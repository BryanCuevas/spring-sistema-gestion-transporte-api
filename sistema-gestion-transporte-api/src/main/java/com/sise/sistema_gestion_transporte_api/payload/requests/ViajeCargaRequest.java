package com.sise.sistema_gestion_transporte_api.payload.requests;

import com.sise.sistema_gestion_transporte_api.entities.Carga;
import com.sise.sistema_gestion_transporte_api.entities.Viaje;

import lombok.Data;

@Data
public class ViajeCargaRequest {
    private Viaje viaje;

    private Carga carga;

    public void setViaje(Integer idViaje) {
        this.viaje = new Viaje();
        this.viaje.setIdViaje(idViaje);
    }

    public void setCarga(Integer idCarga) {
        this.carga = new Carga();
        this.carga.setIdCarga(idCarga);
    }

}
