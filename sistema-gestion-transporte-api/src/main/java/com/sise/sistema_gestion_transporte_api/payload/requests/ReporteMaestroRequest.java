package com.sise.sistema_gestion_transporte_api.payload.requests;

import java.util.List;

import lombok.Data;

@Data
public class ReporteMaestroRequest {
    private String tituloReporte;
    private String tituloIncidenciasReporte;
    private List<ReporteCabeceraRequest> cabeceras;
    private ReporteTablaRequest tabla;
    private ReporteTablaIncidenciasRequest tablaIncidencias;

}
