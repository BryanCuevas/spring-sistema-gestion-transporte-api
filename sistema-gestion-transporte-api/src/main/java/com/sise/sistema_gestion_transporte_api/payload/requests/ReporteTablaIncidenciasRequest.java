package com.sise.sistema_gestion_transporte_api.payload.requests;

import java.util.List;

import lombok.Data;

@Data
public class ReporteTablaIncidenciasRequest {
    private List<String> cabeceras;
    private List<List<String>> data;

}
