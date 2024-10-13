package com.sise.sistema_gestion_transporte_api.payload.requests;

import lombok.Data;

@Data
public class ReporteCabeceraRequest {
    private String campo;
    private String contenido;
    
}
